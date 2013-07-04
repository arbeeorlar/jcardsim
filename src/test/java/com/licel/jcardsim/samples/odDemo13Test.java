/*
 * Copyright 2013 Licel LLC.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.licel.jcardsim.samples;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Properties;
import junit.framework.TestCase;
import com.licel.jcardsim.base.SimulatorSystem;
import com.licel.jcardsim.utils.APDUScriptTool;

/**
 * Test javacard sample (odDemo1-3).
 */
public class odDemo13Test extends TestCase {

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of javacard sample (odDemo1-3).
     */
    public void testExecuteCommands() throws Exception {

        System.out.println("executeCommands");

        Properties cfg = new Properties();
        cfg.setProperty("com.licel.jcardsim.smartcardio.applet.0.AID", "a00000006203010c070103");
        cfg.setProperty("com.licel.jcardsim.smartcardio.applet.0.Class", "com.licel.jcardsim.samples.odSample.packageA.A");
        cfg.setProperty("com.licel.jcardsim.smartcardio.applet.1.AID", "a00000006203010c070203");
        cfg.setProperty("com.licel.jcardsim.smartcardio.applet.1.Class", "com.licel.jcardsim.samples.odSample.packageB.B");

        StringBuilder sb = new StringBuilder();
        sb.append("powerup;\n");
        sb.append("// create Applet A's instance\n");
        sb.append("0x80 0xB8 0x00 0x00 0x0D 0x0B 0xA0 0x00 0x00 0x00 0x62 0x03 0x01 0x0C 0x07 0x01 0x03 0x00 0x7F;\n");
        sb.append("// 90 00 SW_NO_ERROR\n");
        sb.append("//select Applet A's instance\n");
        sb.append("0x00 0xA4 0x04 0x00 0x0B 0xA0 0x00 0x00 0x00 0x62 0x03 0x01 0x0C 0x07 0x01 0x03 0x7F;\n");
        sb.append("// 90 00 SW_NO_ERROR\n");
        sb.append("//analyze all attributes gone\n");
        sb.append("0xC0 0x16 0x00 0x00 0x00 0x7F;\n");
        sb.append("// 90 00 SW_NO_ERROR\n");
        sb.append("//Scenario 2 - Applet deletion\n");
        sb.append("//delete Applet A's instance\n");
        sb.append("0x80 0xc4 0x01 0x00 0x0C 0x0B 0xA0 0x00 0x00 0x00 0x62 0x03 0x01 0x0C 0x07 0x01 0x03 0x7F;\n");
        sb.append("// 90 00 SW_NO_ERROR\n");
        sb.append("//Delete Applet A's instance again and erify error\n");
        sb.append("0x80 0xc4 0x01 0x00 0x0C 0x0B 0xA0 0x00 0x00 0x00 0x62 0x03 0x01 0x0C 0x07 0x01 0x03 0x7F;\n");
        sb.append("// 64 43 \n");
        sb.append("//create Applet A's instance again for mem monitoring. Also capture initialMem\n");
        sb.append("0x80 0xB8 0x00 0x00 0x0D 0x0B 0xA0 0x00 0x00 0x00 0x62 0x03 0x01 0x0C 0x07 0x01 0x03 0x00 0x7F;\n");
        sb.append("// 90 00 \n");
        sb.append("//create Applet B's first instance\n");
        sb.append("0x80 0xB8 0x00 0x00 0x0D 0x0B 0xA0 0x00 0x00 0x00 0x62 0x03 0x01 0x0C 0x07 0x02 0x03 0x00 0x7F;\n");
        sb.append("// 90 00 \n");
        sb.append("//create Applet B's second instance with new AID\n");
        sb.append("0x80 0xB8 0x00 0x00 0x1B 0x0B 0xA0 0x00 0x00 0x00 0x62 0x03 0x01 0x0C 0x07 0x02 0x03 0x0E 0x0B 0xA0 0x00 0x00 0x00 0x62 0x03 0x01 0x0C 0x07 0x02 0x0B 0x00 0x00 0x7F;\n");
        sb.append("// 90 00 \n");
        sb.append("//Delete Applet B's first instance and verify error\n");
        sb.append("0x80 0xc4 0x01 0x00 0x0C 0x0B 0xA0 0x00 0x00 0x00 0x62 0x03 0x01 0x0C 0x07 0x02 0x03 0x7F;\n");
        sb.append("//64 48\n");
        sb.append("//No Applet deletion because of shared reference.\n");
        sb.append("//select Applet A's instance\n");
        sb.append("0x00 0xA4 0x04 0x00 0x0B 0xA0 0x00 0x00 0x00 0x62 0x03 0x01 0x0C 0x07 0x01 0x03 0x7F;\n");
        sb.append("// 90 00 \n");
        sb.append("//Make Applet A's instance get a shareable reference to Applet B's first instance\n");
        sb.append("0xc0 0x21 0x00 0x00 0x00 0x7F;\n");
        sb.append("// 90 00 \n");
        sb.append("//Try deleting Applet B's first instance & Applet B's second instance and verify error.\n");
        sb.append("0x80 0xc4 0x02 0x00 0x18 0x0B 0xA0 0x00 0x00 0x00 0x62 0x03 0x01 0x0C 0x07 0x02 0x03 0x0B 0xA0 0x00 0x00 0x00 0x62 0x03 0x01 0x0C 0x07 0x02 0x0B 0x7F;\n");
        sb.append("//64 48\n");
        sb.append("//select Applet A's instance\n");
        sb.append("0x00 0xA4 0x04 0x00 0x0B 0xA0 0x00 0x00 0x00 0x62 0x03 0x01 0x0C 0x07 0x01 0x03 0x7F;\n");
        sb.append("// 90 00 \n");
        sb.append("//Lose reference from A. Also calls Object Deletion\n");
        sb.append("0xc0 0x22 0x00 0x00 0x00 0x7F;\n");
        sb.append("// 90 00 \n");
        sb.append("//Delete applet B's first instance & applet B's second instance\n");
        sb.append("0x80 0xc4 0x02 0x00 0x18 0x0B 0xA0 0x00 0x00 0x00 0x62 0x03 0x01 0x0C 0x07 0x02 0x03 0x0B 0xA0 0x00 0x00 0x00 0x62 0x03 0x01 0x0C 0x07 0x02 0x0B 0x7F;\n");
        sb.append("// 90 00\n");
        sb.append("//create Applet B's first instance again\n");
        sb.append("0x80 0xB8 0x00 0x00 0x0D 0x0B 0xA0 0x00 0x00 0x00 0x62 0x03 0x01 0x0C 0x07 0x02 0x03 0x00 0x7F;\n");
        sb.append("// 90 00 \n");
        sb.append("//create Applet B's second instance with new AID again\n");
        sb.append("0x80 0xB8 0x00 0x00 0x1B 0x0B 0xA0 0x00 0x00 0x00 0x62 0x03 0x01 0x0C 0x07 0x02 0x03 0x0E 0x0B 0xA0 0x00 0x00 0x00 0x62 0x03 0x01 0x0C 0x07 0x02 0x0B 0x00 0x00 0x7F;\n");
        sb.append("// 90 00\n");
        sb.append("//select Applet B's second instance\n");
        sb.append("0x00 0xA4 0x04 0x00 0x0B 0xA0 0x00 0x00 0x00 0x62 0x03 0x01 0x0C 0x07 0x02 0x0B 0x7F;\n");
        sb.append("// 90 00\n");
        sb.append("//introduce reference from Applet B's first instance to Applet B's second instance\n");
        sb.append("0x80 0x12 0x00 0x00 0x00 0x7F;\n");
        sb.append("// 90 00\n");
        sb.append("//Delete applet B's second instance\n");
        sb.append("0x80 0xc4 0x01 0x00 0x0C 0x0B 0xA0 0x00 0x00 0x00 0x62 0x03 0x01 0x0C 0x07 0x02 0x0B 0x7F;\n");
        sb.append("// 90 00\n");
        sb.append("//Delete applet B's first instance\n");
        sb.append("0x80 0xc4 0x01 0x00 0x0C 0x0B 0xA0 0x00 0x00 0x00 0x62 0x03 0x01 0x0C 0x07 0x02 0x03 0x7F;\n");
        sb.append("// 90 00 \n");
        sb.append("//select Applet A's instance\n");
        sb.append("0x00 0xA4 0x04 0x00 0x0B 0xA0 0x00 0x00 0x00 0x62 0x03 0x01 0x0C 0x07 0x01 0x03 0x7F;\n");
        sb.append("// 90 00 \n");
        sb.append("//verify all mem returned\n");
        sb.append("0xC0 0x18 0x00 0x00 0x00 0x7F;\n");
        sb.append("// 90 00 \n");
        sb.append("// Scenario - package deletion\n");
        sb.append("//select installer\n");
        sb.append("0x00 0xA4 0x04 0x00 0x09 0xa0 0x00 0x00 0x00 0x62 0x03 0x01 0x08 0x03 0x7F;\n");
        sb.append("// 90 00 \n");
        sb.append("//create Applet B's first instance again for testing package deletion\n");
        sb.append("0x80 0xB8 0x00 0x00 0x0D 0x0B 0xA0 0x00 0x00 0x00 0x62 0x03 0x01 0x0C 0x07 0x02 0x03 0x00 0x7F;\n");
        sb.append("// 90 00 \n");
        sb.append("//Delete package B and verify error\n");
        sb.append("0x80 0xc0 0x00 0x00 0x0B 0x0A 0xA0 0x00 0x00 0x00 0x62 0x03 0x01 0x0C 0x07 0x0B 0x7F;\n");
        sb.append("// 64 4d\n");
        sb.append("//Delete package C and verify error\n");
        sb.append("0x80 0xc0 0x00 0x00 0x0B 0x0A 0xA0 0x00 0x00 0x00 0x62 0x03 0x01 0x0C 0x07 0x03 0x7F;\n");
        sb.append("// 64 4c\n");
        sb.append("//Delete Applet B's first instance & package B\n");
        sb.append("0x80 0xc2 0x00 0x00 0x0B 0x0A 0xA0 0x00 0x00 0x00 0x62 0x03 0x01 0x0C 0x07 0x0B 0x7F;\n");
        sb.append("// 90 00 \n");
        sb.append("//Delete packageC\n");
        sb.append("0x80 0xc0 0x00 0x00 0x0B 0x0A 0xA0 0x00 0x00 0x00 0x62 0x03 0x01 0x0C 0x07 0x03 0x7F;\n");
        sb.append("// 90 00\n");
        sb.append("powerdown;\n");

        InputStream commandsStream = new ByteArrayInputStream(sb.toString().replaceAll("\n", System.getProperty("line.separator")).getBytes());     
        boolean isException = true;
        try {
            SimulatorSystem.resetRuntime();
            APDUScriptTool.executeCommands(cfg, commandsStream, null);
            isException = false;
        } catch (Throwable t) {
            t.printStackTrace();
        }
        assertEquals(isException, false);
        commandsStream.close();
    }
}
