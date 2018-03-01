# Customer Consume SOAP Web Services 

## Gradle configuration to Client classes Web Services generator

In the file build.gradle we have a section that allow us configurate the WSDL location to generate the client web service stubs.

````task genJaxb {
	ext.sourcesDir = "${buildDir}/generated-sources/jaxb"
	ext.classesDir = "${buildDir}/classes/jaxb"
	ext.schema = "http://www.webservicex.com/stockquote.asmx?WSDL"

	outputs.dir classesDir

	doLast() {
		project.ant {
			taskdef name: "xjc", classname: "com.sun.tools.xjc.XJCTask",
					classpath: configurations.jaxb.asPath
			mkdir(dir: sourcesDir)
			mkdir(dir: classesDir)

			xjc(destdir: sourcesDir, schema: schema,
					package: "hello.wsdl") {
				arg(value: "-wsdl")
				produces(dir: sourcesDir, includes: "**/*.java")
			}

			javac(destdir: classesDir, source: 1.8, target: 1.8, debug: true,
					debugLevel: "lines,vars,source",
					classpath: configurations.jaxb.asPath) {
				src(path: sourcesDir)
				include(name: "**/*.java")
				include(name: "*.java")
			}

			copy(todir: classesDir) {
				fileset(dir: sourcesDir, erroronmissingdir: false) {
					exclude(name: "**/*.java")
				}
			}
		}
	}
}
````
The property **ext.schema** represent the WSDL localtion.
``
ext.schema = "http://www.webservicex.com/stockquote.asmx?WSDL"
``

And the property **package:** represent the packager where the client stub classes will be generated.

``
package: "hello.wsdl") {
``

## Generate domain objects based on a WSDL

To create the Client Web services classes run on command line:

``gradle build``

JAXB domain object generation process has been wired into the build tool’s lifecycle so there are no extra steps to run.

The classes generated will be on the **build** directory behind **generated-sources/jaxb** for example it is the next structure:
````
├── generated-sources
│   └── jaxb
│       └── hello
│           └── wsdl
│               ├── GetQuote.java
│               ├── GetQuoteResponse.java
│               ├── ObjectFactory.java
│               └── package-info.java
````

##Run the project on development:

To run a project in place without building a jar first you can use the “bootRun” task:

`
$ gradle bootRun
`

If devtools has been added to your project it will automatically monitor your application for changes. Alternatively, you can also run the application so that your static classpath resources (i.e. in src/main/resources by default) are reloadable in the live application, which can be helpful at development time.

`bootRun {
    addResources = true
}`

## Build

``
gradle build 
``

## Run the Module

``
java --add-modules java.xml.bind,java.xml.ws,java.xml.ws.annotation -jar build/libs/customer-integration-1.0.0-RC1.jar
``
