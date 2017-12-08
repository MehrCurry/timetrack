package hello

import com.vaadin.data.Binder
import com.vaadin.event.ShortcutAction
import com.vaadin.server.FontAwesome
import com.vaadin.spring.annotation.SpringComponent
import com.vaadin.spring.annotation.UIScope
import com.vaadin.ui.Button
import com.vaadin.ui.CssLayout
import com.vaadin.ui.TextField
import com.vaadin.ui.VerticalLayout
import com.vaadin.ui.themes.ValoTheme
import org.springframework.beans.factory.annotation.Autowired

/**
 * A simple example to introduce building forms. As your real application is probably much
 * more complicated than this example, you could re-use this form in multiple places. This
 * example component is only used in VaadinUI.
 *
 *
 * In a real world application you'll most likely using a common super class for all your
 * forms - less code, better UX. See e.g. AbstractForm in Viritin
 * (https://vaadin.com/addon/viritin).
 */
@SpringComponent
@UIScope
class CustomerEditor @Autowired
constructor(private val repository: CustomerRepository) : VerticalLayout() {

    /**
     * The currently edited customer
     */
    private var customer: Customer? = null

    /* Fields to edit properties in Customer entity */
    internal var firstName = TextField("First name")
    internal var lastName = TextField("Last name")

    /* Action buttons */
    internal var save = Button("Save", FontAwesome.SAVE)
    internal var cancel = Button("Cancel")
    internal var delete = Button("Delete", FontAwesome.TRASH_O)
    internal var actions = CssLayout(save, cancel, delete)

    internal var binder = Binder<Customer>(Customer::class.java)

    init {

        addComponents(firstName, lastName, actions)

        // bind using naming convention
        binder.bindInstanceFields(this)

        // Configure and style components
        isSpacing = true
        actions.styleName = ValoTheme.LAYOUT_COMPONENT_GROUP
        save.styleName = ValoTheme.BUTTON_PRIMARY
        save.setClickShortcut(ShortcutAction.KeyCode.ENTER)

        // wire action buttons to save, delete and reset
        save.addClickListener { _ -> repository.save<Customer>(customer) }
        delete.addClickListener { _ -> repository.delete(customer) }
        cancel.addClickListener { _ -> editCustomer(customer) }
        isVisible = false
    }

    interface ChangeHandler {

        fun onChange()
    }

    fun editCustomer(c: Customer?) {
        if (c == null) {
            isVisible = false
            return
        }
        val persisted = c.id != null
        if (persisted) {
            // Find fresh entity for editing
            customer = repository.findOne(c.id)
        } else {
            customer = c
        }
        cancel.isVisible = persisted

        // Bind customer properties to similarly named fields
        // Could also use annotation or "manual binding" or programmatically
        // moving values from fields to entities before saving
        binder.setBean(customer)

        isVisible = true

        // A hack to ensure the whole form is visible
        save.focus()
        // Select all text in firstName field automatically
        firstName.selectAll()
    }

    fun setChangeHandler(h: ChangeHandler) {
        // ChangeHandler is notified when either save or delete
        // is clicked
        save.addClickListener { _ -> h.onChange() }
        delete.addClickListener { _ -> h.onChange() }
    }

}