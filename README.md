# fenxui
JavaFX Wrapper for annotation-based programming designed to make simple apps simpler.
## Getting Started
* Clone the source code
* Build fenxui using gradle <i>install</i> target in the <b>fenxui</b> sub-project
```
C:\Users\Me\Documents\GitHub\fenxui>cd fenxui

C:\Users\Me\Documents\GitHub\fenxui\fenxui>gradle install
```

* Add to your app
```
repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    compile group: 'org.fenxui', name: 'fenxui', version: '1.0+'
```

* Make your main class extend <b>FenxuiApplication</b> and provide an annotated view model for introspection.
```java
public class SampleApp extends FenxuiApplication {

	@Override
	public FenxuiConfig getFenxuiConfig() {
		return new FenxuiConfig.Builder()
				.css(SampleApp.class.getResource("/css/SampleApp.css"))
				.title("Sample Fenxui App")
				.build();
	}

	@Override
	public FenxuiPrototype getFenxuiPrototype() {
		return JFoenixPrototype.newInstance(new SampleViewModel(), () -> {
			log.info("Application closing");
			Platform.exit();
		});
	}

	public static void main(String[] args) {
		launch(args);
	}
```
* That's it.
### Prerequisites
* You must have a JDK that supports JavaFX or have your JDK configured to work with OpenJFX
* You must have Gradle installed to be able to build fenxui

## Annotations
### Layout
#### @Menu
declares a class holding a menu
* cssClass (default: "menuitem")
* orientation (default: VERTICAL)
* minimumWidth (default: 185)
* minimumHeight (default: 23)
example<br>
```java
@Menu
public class SampleViewModel extends FenxuiViewModel {
```
![A sample side menu](https://user-images.githubusercontent.com/3435255/50727765-97b49a80-10ed-11e9-97d6-b7dfb865b265.png)

#### @MenuItem
declares a menu item or tab
* value (Text label)
* cssClass (default: "menu-text")
* required (flag if user input on the page is required)

example
```java
@MenuItem("Server Settings")
private final ServerSettings serverSettings = new ServerSettings(this);
```
#### @AppPage
Title/stylings for page
* value (Title)
* cssClass (default: "apppage")

example<br>
```java
@AppPage("Server Settings")
public class ServerSettings {
```

### @FormField
Declares a user-input field
* label
* type (default: TEXT)
* section (If the field is hidden by default (ADDITIONAL) or displayed by default (DEFAULT); default DEFAULT)

example
```java
@FormField(label = "Server")
private final StringProperty machine = new SimpleStringProperty();
```

Note, the TextField/ComboBox/CheckBox, etc value will be saved on the StringProperty field it decorates.  This allows you to use the field values directly.
![Supported form field types](https://user-images.githubusercontent.com/3435255/50727763-97b49a80-10ed-11e9-9fdd-3143e7376a37.png)

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
![Conditional validator on value in same form](https://user-images.githubusercontent.com/3435255/50727761-97b49a80-10ed-11e9-85b1-e47e2b4d2e20.png)
```java
@AppPage("Tab B")
public class SimpleTabPageB {

  @FormField(label = "Required when Tab A enabled")
  @Validator(type = ValidatorOptions.REQUIRED, message = "From email is required", evalExpression="#{SimpleTabPageA.enable} eq 'true'")
  private final StringProperty conditionallyRequiredField = new SimpleStringProperty();

@AppPage("Tab A")
public class SimpleTabPageA  {

	@FormField(label = "Enable", type=CHECKBOX)
	@CheckBoxValue(checked="true", unchecked="false")
	private final StringProperty enable = new SimpleStringProperty("false");
```
![Conditional validator on value in different form](https://user-images.githubusercontent.com/3435255/50727762-97b49a80-10ed-11e9-8591-ebca8b94b89e.png)

## Built With
* [Gradle](https://gradle.org/) - Dependency Management
* [Foenix](https://github.com/jfoenixadmin/JFoenix) - UI styleing
## Contributing

## Versioning

## Authors

* **Ben Arnold** - *Initial work*

See also the list of [contributors](https://github.com/benfarnold/fenxui/contributors) who participated in this project.

## License

This project is licensed under the Apache License 2.0 - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgment
