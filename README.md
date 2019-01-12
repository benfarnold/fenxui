# fenxui
JavaFX Wrapper for annotation-based programming designed to make simple apps simpler.

## Annotations
### Layout
#### @Menu
declares a class holding a menu
* cssClass (default "menuitem")
* orientation (default VERTICAL)

example<br>
```java
@Menu
public class SampleViewModel extends FenxuiViewModel {
```
#### @MenuItem
declares a menu item or tab
* value (Text label)
* cssClass (default "menupage-link")
* required (flag if user input on the page is required)

example
```java
@MenuItem("Server Settings")
private final ServerSettings serverSettings = new ServerSettings(this);
```
#### @AppPage
Title/stylings for page
* value (Title)
* cssClass (default "apppage")

example<br>
```java
@AppPage("Server Settings")
public class ServerSettings {
```

### @FormField
Declares a user-input field
* label
* factory (default "text") supported out the box values are listed as constants in FactoryInitContext.FieldPrototype.  You also have the ability to register custom field types as long as you also register the factory.
* section (If the field is hidden by default (ADDITIONAL) or displayed by default (DEFAULT); default DEFAULT)
* dynamicWidth (default false).  True if the field width should be bound to the page width. 

example<br>
```java
@FormField(label = "Server")
private final StringProperty machine = new SimpleStringProperty();
```

<b>Note</b>: the TextField/ComboBox/CheckBox, etc value will be saved on the Property field it decorates.  This allows you to use the field values directly.
### @ExpressionFormField
Declares a field that is populated by a JEXL expression.
* label
* expression  (ie: "#{fieldA} + #{fieldB} + #{OtherClass.fieldZ}")
* factory (default "text") supported out the box values are listed as constants in FactoryInitContext.FieldPrototype.  You also have the ability to register custom field types as long as you also register the factory.
* dynamicWidth (default false).  True if the field width should be bound to the page width. 
* readOnly (default true).

example<br>
```java
@ExpressionFormField(label = "BMI", expression = "#{weight} / (#{heightInchesTotal} * #{heightInchesTotal} * 1.0) * 7.03", factory = "percent")//'1.0' -> avoid integer division
private ObjectProperty<BigDecimal> bmi = new SimpleObjectProperty<>(BigDecimal.ZERO);
```

### Value Providers
#### @CheckBoxValue
The value to save on the bound StringProperty if the checkbox is checked or unchecked
* checked
* unchecked

```java
@CheckBoxValue(checked="true", unchecked="false")
private final StringProperty enableNotifications = new SimpleStringProperty("false");
```

#### @ValueProviderValue
Provides key-value pair for ComboBox. Decorate field for multiple values.
* key (the value to save on the field)
* value (the value to display in the ComboBox)

```java
@ValueProviderValue(key="ORCL", value= "Oracle")
@ValueProviderValue(key="PGRS", value= "PostgreSQL")
private final StringProperty serverType = new SimpleStringProperty();
```

### Validation
#### @Validator
Enables validation on the field.  A message will be displayed below the field when validation fails.  You can have multiple validators per field
* type 
* message
* evalExpression (JEXL-expression that controls if validation should be enforced on supporting validators)

```java
@Validator(type = ValidatorOptions.REQUIRED, message = "Port is required")
@Validator(type = ValidatorOptions.NUMERIC, message = "Port must be numeric")
private final StringProperty port = new SimpleStringProperty("8080");

@FormField(label = "Enable notifications", type=CHECKBOX)
@CheckBoxValue(checked="true", unchecked="false")
private final StringProperty enableNotifications = new SimpleStringProperty("false");

@FormField(label = "From email")
@Validator(type = ValidatorOptions.REQUIRED, message = "From email is required", evalExpression="#{enableNotifications} eq 'true'")
private final StringProperty senderAddress = new SimpleStringProperty();
```
