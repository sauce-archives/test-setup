# test-setup
A higher-level Appium test setup.

This setup allows you to write suites of Appium tests for both iOS and Android, and helps you keep everything tidy in the meantime.

## Structure
Every screen in the application has its own class, which provides all the actions that can be done on said screen.
Every screen-test pair has its own class, e.g. the LoginTest class will contain all the tests you run on the LoginScreen, using the actions specified in LoginScreen.
Every Screen extends AbstractScreen and every Test extends AbstractTest. These two classes provide the common elements to the subclasses, keeping the code tidy.
