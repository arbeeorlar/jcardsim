**Offical site of the jCardSim project**: http://jcardsim.org

**Congratulations jCardSim has won the 2013 Duke's Choice Award!**: https://www.java.net/dukeschoice


---


**Please note that the project was moved to** https://github.com/licel/jcardsim. Now GitHub repository is an official repo for the project.


---



**jCardSim** is open-source library contains implementation of Java Card API, v.2.2.1:
  * javacard.framework.`*`
  * javacard.framework.security.`*`
  * javacardx.crypto.`*`

**Key Features:**
  * The ability of rapid applications prototyping
  * Ease of writing Unit-tests (5 lines of code)
```
//1. create simulator
Simulator simulator = new Simulator();
//2. install applet
simulator.installApplet(appletAID, HelloWorldApplet.class);
//3. select applet
simulator.selectApplet(appletAID);
//4. send apdu
ResponseAPDU response = simulator.transmitCommand(new CommandAPDU((byte)0x80, (byte)0x01, (byte)0x00, (byte)0x00));
//5. check response
assertEquals(0x9000, response.getSW());
```
  * Emulation of Java Card Terminal, ability to use javax.smartcardio
  * APDU scripting (scripts are compatible with apdutool from Java Card Development Kit)
  * Ease of verification tests creation (Common Criteria)

**JavaDoc:** https://jcardsim.googlecode.com/svn/trunk/javadoc/index.html

**Latest stable release:** https://jcardsim.googlecode.com/svn/trunk/jcardsim-2.2.1-all.jar

**Snapshot Maven Repository:** https://oss.sonatype.org
```
<dependency>
  <groupId>com.licel</groupId>
  <artifactId>jcardsim</artifactId>
  <version>2.2.1-SNAPSHOT</version>
</dependency>
```


**Developer**: [LICEL LLC](https://licel.ru) develops embedded solutions based on Java and Java Card technologies (payment systems, medicine and security). The company provides services to protect software products from violation of the licensing policies.

**How to help jCardSim?**

Join team of jCardSim developers.
Visit the site of our project - [Stringer Java Obufscator](https://jfxstore.com/stringer) (we hope it could be useful for you) and share it with your friends who may be interested.

**Third-party libraries**: Legion of the Bouncy Castle Java

**Trademarks**: Oracle, Java and Java Card are trademarks of Oracle Corporation.