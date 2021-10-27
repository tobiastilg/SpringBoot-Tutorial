# FullStackDevelopment mit Spring Boot #

Vollständige Spring Boot Demo-Applikation (Vertiefung)

- [FullStackDevelopment mit Spring Boot](#fullstackdevelopment-mit-spring-boot)
  - [Spring Boot](#spring-boot)
  - [API](#api)
  - [DevTools](#devtools)
  - [H2](#h2)
  - [Ports & Adapters Architecture](#ports--adapters-architecture)
  - [Hibernate](#hibernate)
  - [Logger](#logger)
  - [Lombok](#lombok)
  - [Exception Handeling](#exception-handeling)
  - [MySQL](#mysql)
  - [Unit Testing](#unit-testing)
  - [Spring Boot Profiles](#spring-boot-profiles)
  - [JAR File](#jar-file)
  - [Actuator](#actuator)

## Spring Boot

__Spring__ ist ein sehr umfangreichers Java Framework. Wichtig ist im vorhinen eine gute Konfiguratiuon der Applikation um möglichst effizient ein Programm zu entwickeln. Deshalb kann __Spring Boot__ verwendet werden, das wie eine Extension für das Spring Framework ist (selbes Framework!) und einem eine menge Konfigurationsarbeit abnimmt.

__Dependencys__ sind in diesem Fall der zentrale Konfigurationspunkt. Es gibt gewisse Starter Templates (zB JPA Template), die dann alle benötigten Dependencies für die Applikation miteinbeziehen (es können jederzeit weitere hinzugefügt werden).

Die __Autoconfiguration__ ist ein weiterer Vorteil von Spring Boot. Die Konfigurationenh von Dependencies und Libraries wird automatisch von dem Framework übernomen.

Unter __Dependency Injection__ versteht man das Einbringen von Abhängigkeiten, was soviel bedeutet, das beispielsweise ein Objekt bei der Initialisierung ein anderes Objekt benötigt. Das benötigte Objekt wird aber nicht von einem selbst (Schlüsselwort `new`) erzeugt, sondern ist irgendwo hinterlegt.
Spring Boot kann einem diesen Vorgang komplett abnehmen, damit man nur mehr das gewünschte Objekt verwenden muss. Bei großen Programmen mit vielen Klassen ist dieser Vorgang essenziell um effizient arbeiten zu können.

start.spring.io hilft uns ein Spring Boot Project zu einzurichten und zu konfigurieren (ähnlich wie IntelliJ). Da dieses Beispiel auf einem Maven Projekt basiert, dann auch über maven das Projekt gestartet werden (über das Spring Boot Plugin: `mvn spring-boot:run`)

## API

Mithilfe von `@RestController` kann ein API Controller erstellt werden. Jeder Controller ist außerdem ein `@Component` und daher innerhalb des SpringBoot Containers (Klasse steht immer zur Verfügung).

Ein `@RequestMapping` kann eine Methode, bei Aufruf des gewünschten Values (im folgenden Fall: "localhost:8080/") über die gewünschte HTTP-Methode (zB GET, Post) aufrufen.

```java
@RequestMapping(value = "/", method = RequestMethod.GET)
public String helloWorld() {
    return "Welcome to Spring Boot!";
}
```

Anstatt aber `@RequestMapping` zu verwenden kann die Methode direkt in der Annotation festgelegt werden.

```java
@GetMapping("/")
```

Wie bereits erwähnt wird über die Autoconfiguration in Spring Boot vieles automatisch eingerichtet. Sollte es jedoch etwas geben, das nicht standardmäßig hinterlegt ist oder man eine eigene Konfiguration vorzieht, so gibt es die `application.properties` File. Darin lassen sich Einstellungen konfigurieren, die für Applikation gelten sollen (zB der Serverport). In der Spring Boot Dokumentation kann nachgelesen werden, welche Einstellung welchem Zweck dient.

## DevTools

Zur schnelleren und einfacheren Entwicklung können die Spring Boot Dev-Tools verwendet werden. Ist die Dependency geladen, können in IntelliJ einige weitere Einstellungen angepasst werden. Wichtige sind beispielsweise:

* Settings | Advanced Settings | Compiler --> Allow auto-make to start even if...
* File | Settings | Build, Execution, Deployment | Compiler --> Build Project automatically

## H2

H2 ist eine in-memory Datenbank (Datenbanksystem das den Arbeitsspeicher des Computers nutzt) die oft zu Testzwecken verwendet wird. Die Einstellungen dazu können in der `application.properties` File festgelegt werden, nachdem die Dependency eingefügt wurde.

```properties
spring.h2.console.enabled=true
spring.datasource.url=jdbc:h2:mem:dcbapp
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
```

Da die Konsole hier aktiviert wird, kann man sich über `http://localhost:8082/h2-console` auf die Datenbank verbinden.

Über einen REST-Client (zB Insomnia, Postman)kann die Datenbank getestet werden.

## Ports & Adapters Architecture

Durch eine gute Architektur kann eine saubere Kopplung der Applikation gewährleistet werden.

* Repo kommuniziert mit meinen Daten (Domäin) in der Datenbank (Entity)
* Service (UseCases/Geschäftslogik) hält Repo und gibt Aktionen vor
* Controller hält Service und stellt Schnittstelle dar (API - MVC)

_MVC: Controller schickt das Model an den View_

_CQRS: Command-Query-Responsibility-Segregation_

Über das Repository greifen wir auf die Informationen in der Datenbank zu. Über die JPA Schnittstelle können die Daten ausgelesen werden. Weiters stellt uns das JpaRepository, CRUD-Operationen (über die ID) standardmäßig zur Verfügung. Sollte es für eine Funktion keine Default-Methode geben, so kann mit richtigem Syntax (keyword) automatisch die gesuchte Funktion implementiert werden. Beispielsweise: 

```java
public Department findByDepartmentNameIgnoreCase(String departmentName);
```

Nachzulesen in der Spring Boot JPA Dokumentation
(https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation)

Sollte aber kein passendes JPA-Query (JPQL) existieren, kann mit dem `@Query` Tag, die komplette Query angegeben werden, die die Methode aufrufen soll. Erklärungen und Beispielimplementierung findet ebenso in der Dokumentation (5.1.3 Query Methods, Using @Query)

## Hibernate

Nachdem die APIs (MVC) erstellt wurden, müssen die Anfragen auch validiert werden. Mithilfe von Hibernate ist dies ganze einfach möglich.

Nachdem die benötigte Dependency eingefügt wurde, können Attribute in der Entity-Klasse validiert werden.
Dokumentation: https://hibernate.org/validator/documentation/

## Logger

Mithilfe eines Loggers können einfache Informationen, die unter anderem beim Debuggen helfen, ausgegeben werden. SLF4J (Simple Logging Facade for Java) kann beispielsweise einfach implementiert werden.

## Lombok

Getter, Setter, der Konstruktor usw. einer Klasse sind schnell erstellt und werden häufig gebraucht. Besonders leserlich ist der Code dadurch aber nicht. Mithilfe von Lombok erspart man sich die Arbeit die verschiedensten Methoden zu erstellen und der Quellcode bleibt sauber. (https://projectlombok.org/features/all)

## Exception Handeling

Um bei fehlerhaften Anfragen nicht einen unübersichtlichen Errorlog lesen zu müssen, können einfache Exceptions geworften werden.
Um die einzelnen Exceptions nicht manuell, beispielsweise im Controller, behandeln zu müssen, kann der `ResponseEntityExceptionHandler` verwendet werden. 

Der erstelle `RestResponseEntityExceptionHandler` erbt von diesem und ist unsere Anprechstation bei Exceptions. In unserem Servicelayer (Geschäftslogik) wird bei einer fehlerhaften Ausführung eine Exception geworfen. Diese muss, wie bereits erwähnt, nicht von einem behandelt werden, das Sping Boot auch hierfür eine effizienter Variate bereitstellt.

Mit der `@ControllerAdvice` Notation für eine Klasse, werden alle in Controller geworfenen Exceptions, an diese Klasse weitergeleitet um dort behandelt zu werden. Der sogenannte `ExceptionHandler` hält dann für jede Exception eine Methode, in der diese Exception behandelt wird. Die Methoden geben dann 

Im folgenden Beispiel wurde eine Domänen-Klasse `ErrorMessage` ersetllt die dann als ResponseObject bzw. ResponseEntity zurückgegeben wird. So können in Spring Boot Exception sauber behandelt werden.

```java
@ControllerAdvice
@ResponseStatus
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{

    @ExceptionHandler(DepartmentNotFoundException.class)
    public ResponseEntity<ErrorMessage> departmentNotFoundException 
    (DepartmentNotFoundException exception, WebRequest request) {
        ErrorMessage message = new ErrorMessage(HttpStatus.NOT_FOUND, 
        exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }
}
```

## MySQL

Ansatt der H2 in-memory Datenbank kann auch eine externe (in diesem Fall) MySQL Datenbank benutzt werden, damit die Daten erhalten bleiben. Da bei mir MySQL in einem Docker Container, auf einem virtuellen Server läuft, sieht die `application.properties` wiefolgt aus:

```properties
spring.jpa.hibernate.ddl-auto=update
spring.datasource.url=jdbc:mysql://10.77.0.110:3306/dcbapp
spring.datasource.username=root
spring.datasource.password=123
spring.datasource.driver-class-name=com.mysql.jdbc.Driver
spring.jpa.show-sql: true
```

## Unit Testing

Mithilfe von unterschiedlichen Libaries, wie JUnit, können Tests in jedem Layer durchgeführt werden. Wird aber beipsielsweise ein Controller getestet, so ist seine Funktionalität vom Servicelayer abhängig. `Mocking` bewirkt, dass, unabhängig vom Servicelayer, direkt das gewünschte Ergebnis an den Controller geliefert wird. Es sollen auch beim testen des Repositorys keine Daten gespeichert, geändert oder gelöscht werden. Auch hierzu werden die Daten `gemockt`.
Mit IntelliJ kann man automatisch Testklassen erzeugen lassen, die dann implementiert werden können.

Es gibt unterschiedliche Vorgehensweisen den Service-, Controller- und Repositorylayer zu testen. Genauere Beschreibung siehe Kommentare im Programm.

## Spring Boot Profiles

Die `application.properties` Datei kann auch als `application.yaml` gespeichert werden. So wird die config Datei übersichtlicher. Zudem lassen sich ganz einfach verschiedene Spring Boot Profile anlegen, die dann je nach Bedarf abgeändert werden können (zB andere Datenbank). So muss nur das Profil gewechselt werden, wenn eine andere config benötigt wird.

```yaml
spring:
  profiles:
    active: dev
```

## JAR File

Durch Maven, lässt sich mit einem Befehl eine ausführbare JAR File erzeugen. Möglichweise muss noch ein Plugin nachinstalliert werden (resources).

```
mvn clean install
```

Ausführen lässt sich die Datei dann zB ganz einfach über den Terminal. Wenn man unterschiedliche Profile angelegt hat, kann die JAR Datei mit einem zusätzlichen Attribut (`--spring.profiles.active=profile`) die Applikation mit dem jeweiligen Profil ausführen. Gibt man kein Profil an, so wird das in der config File aktive (zB `active: dev`) Profil, zum Ausführen verwendet. 

```
java -jar .\Spring-boot-tutorial-1.0.0.jar --spring.profiles.active=profile
```

## Actuator

Mithilfe des Actuators (Dependency) können über den URL Pfad `/ancunator`, Abfragen zur Applikation gemacht werden. In der config File können zusätzliche "endpoints" hinzugefügt werden, damit mehr Informationen einsehbar sind.

Eigene Endpoints lassen sich mit der `@Endpoint` Notation erstellen (siehe /actuator/features).