# fenxui
JavaFX Wrapper for annotation-based programming designed to make simple apps simpler.

## Annotations
###Layout
####@Menu
declares a class holding a menu
* cssClass (default "menuitem")
* orientation (default VERTICAL)

example<br>
<code>@Menu<br>
public class SampleViewModel extends FenxuiViewModel {</code>

####@MenuItem
declares a menu item or tab
* value (Text label)
* cssClass (default "menu-text")
* required (flag if user input on the page is required)

example<br>
<code>@MenuItem("Server Settings")<br>
private final ServerSettings serverSettings = new ServerSettings(this);</code>

####@AppPage
Title/stylings for page
* value (Title)
* cssClass (default "apppage")

example<br><code>
@AppPage("Server Settings")<br>
public class ServerSettings {</code>

###@FormField
Declares a user-input field
* label
* type (default TEXT)
* section (If the field is hidden by default (ADDITIONAL) or displayed by default (DEFAULT); default DEFAULT)
<code>
@FormField(label = "Server")
private final StringProperty machine = new SimpleStringProperty();
</code>

Note, the TextField/ComboBox/CheckBox, etc value will be saved on the StringProperty field it decorates.  This allows you to use the field values directly.
###Value Providers
####@CheckBoxValue
The value to save on the bound StringProperty if the checkbox is checked or unchecked
* checked
* unchecked

example<br><code>
@CheckBoxValue(checked="true", unchecked="false")<br>
private final StringProperty enableNotifications = new SimpleStringProperty("false");
</code>

####@ValueProviderValue
Provides key-value pair for ComboBox. Decorate field for multiple values.
* key (the value to save on the field)
* value (the value to display in the ComboBox)

example<br><code>
@ValueProviderValue(key="ORCL", value= "Oracle")<br>
@ValueProviderValue(key="PGRS", value= "PostgreSQL")<br>
private final StringProperty serverType = new SimpleStringProperty();
</code>
###Validation
####@Validator
Enables validation on the field.  A message will be displayed below the field when validation fails.  You can have multiple validators per field
* type 
* message
* evalExpression (JEXL-expression that controls if validation should be enforced on supporting validators)

example<br><code>
@Validator(type = ValidatorOptions.REQUIRED, message = "Port is required")<br>
@Validator(type = ValidatorOptions.NUMERIC, message = "Port must be numeric")<br>
private final StringProperty port = new SimpleStringProperty("8080");<br><br>
@FormField(label = "Enable notifications", type=CHECKBOX)<br>
@CheckBoxValue(checked="true", unchecked="false")<br>
private final StringProperty enableNotifications = new SimpleStringProperty("false");<br><br>
@FormField(label = "From email")<br>
@Validator(type = ValidatorOptions.REQUIRED, message = "From email is required", evalExpression="#{enableNotifications} eq 'true'")<br>
private final StringProperty senderAddress = new SimpleStringProperty();<br>
</code>