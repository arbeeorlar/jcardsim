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
 * Test javacard sample (demo2).
 */
public class demo2Test extends TestCase {

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of javacard sample (demo2).
     */
    public void testExecuteCommands() throws Exception {

        System.out.println("executeCommands");

        Properties cfg = new Properties();
        cfg.setProperty("com.licel.jcardsim.smartcardio.applet.0.AID", "a00000006203010c0201");
        cfg.setProperty("com.licel.jcardsim.smartcardio.applet.0.Class", "com.licel.jcardsim.samples.JavaPurse.JavaPurse");
        cfg.setProperty("com.licel.jcardsim.smartcardio.applet.1.AID", "a00000006203010c0501");
        cfg.setProperty("com.licel.jcardsim.smartcardio.applet.1.Class", "com.licel.jcardsim.samples.JavaLoyalty.JavaLoyalty");
        cfg.setProperty("com.licel.jcardsim.smartcardio.applet.2.AID", "a00000006203010c0601");
        cfg.setProperty("com.licel.jcardsim.smartcardio.applet.2.Class", "com.licel.jcardsim.samples.wallet.Wallet");
        cfg.setProperty("com.licel.jcardsim.smartcardio.applet.3.AID", "a00000006203010c0801");
        cfg.setProperty("com.licel.jcardsim.smartcardio.applet.3.Class", "com.licel.jcardsim.samples.RMIDemo.PurseApplet");
        cfg.setProperty("com.licel.jcardsim.smartcardio.applet.4.AID", "a00000006203010c0a01");
        cfg.setProperty("com.licel.jcardsim.smartcardio.applet.4.Class", "com.licel.jcardsim.samples.SecureRMIDemo.SecurePurseApplet");
        cfg.setProperty("com.licel.jcardsim.smartcardio.applet.5.AID", "a00000006203010c0701");
        cfg.setProperty("com.licel.jcardsim.smartcardio.applet.5.Class", "com.licel.jcardsim.samples.photocard.PhotoCardApplet");

        StringBuilder sb = new StringBuilder();
        sb.append("powerup;\n");
        sb.append("// create JavaPurse\n");
        sb.append("0x80 0xB8 0 0 0x0c 0x0a 0xa0 0 0 0 0x62 0x03 0x01 0x0c 2 0x01 0 0x7F;\n");
        sb.append("// create JavaLoyalty\n");
        sb.append("0x80 0xB8 0 0 0x0c 0x0a 0xa0 0 0 0 0x62 0x03 0x01 0x0c 5 0x01 0 0x7F;\n");
        sb.append("// create wallet applet\n");
        sb.append("0x80 0xB8 0x00 0x00 0x14 0x0a 0xa0 0x0 0x0 0x0 0x62 0x3 0x1 0xc 0x6 0x1 0x08 0 0 0x05 0x01 0x02 0x03 0x04 0x05 0x7F;\n");
        sb.append("// create RMIDemo\n");
        sb.append("0x80 0xB8 0x00 0x00 0x0c 0x0a 0xa0 0x00 0x00 0x00 0x62 0x03 0x01 0xc 0x8 0x01 0x00 0x7F;\n");
        sb.append("// create SecureRMIDemo\n");
        sb.append("0x80 0xB8 0x00 0x00 0x0c 0x0a 0xa0 0x00 0x00 0x00 0x62 0x03 0x01 0xc 0xa 0x01 0x00 0x7F;\n");
        sb.append("// create photocard applet\n");
        sb.append("0x80 0xB8 0x00 0x00 0x0C 0x0A 0xA0 0x00 0x00 0x00 0x62 0x03 0x01 0x0C 0x07 0x01 0x00 0x7F;\n");
        sb.append("/////////////////////////////////////////////////////////////////////\n");
        sb.append("// Initialize JavaPurse\n");
        sb.append("/////////////////////////////////////////////////////////////////////\n");
        sb.append("// Select JavaPurse\n");
        sb.append("0x00 0xa4 0x04 0x00 10 0xa0 0 0 0 0x62 3 1 0xc 2 1 127;\n");
        sb.append("// 90 00 = SW_NO_ERROR\n");
        sb.append("// Initialize Parameter Update\n");
        sb.append("0x80 0x24 0x00 0x00 0x00 0x7F; \n");
        sb.append("//00 00 00 00 0c 1f 63 00 01 90 00 = Purse ID : 0x00000000; ExpDate 12/31/99; PUN 1\n");
        sb.append("//For the second and consecutive runs it can be 69 82\n");
        sb.append("// Complete Parameter Update: CAD ID 0x11223344; Set Master PIN 12345678\n");
        sb.append("0x80 0x26 0x00 0x00 0x1A 0x11 0x22 0x33 0x44 0x00 0x00 0x00 0x00 0xC1 0x08 0x01 0x02 0x03 0x04 0x05 0x06 0x07 0x08 0x00 0x00 0x00 0x00 0x00 0x00 0x00 0x00 0x7F;\n");
        sb.append("// 00 00 00 00 00 00 00 00 90 00 \n");
        sb.append("// For second and consecutive runs it can be 91 04\n");
        sb.append("// Verify PIN : Master PIN\n");
        sb.append("0x00 0x20 0x00 0x81 0x08 0x01 0x02 0x03 0x04 0x05 0x06 0x07 0x08 0x7F;\n");
        sb.append("// 90 00;\n");
        sb.append("// Initialize Parameter Update\n");
        sb.append("0x80 0x24 0x00 0x00 0x00 0x7F;\n");
        sb.append("// 00 00 00 00 0c 1f 63 00 02 90 00 = Purse ID : 0x00000000; ExpDate 12/31/99; PUN 2\n");
        sb.append("// Complete Parameter Update: CAD ID 0x11223344; Set User PIN 1234\n");
        sb.append("0x80 0x26 0x00 0x00 0x16 0x11 0x22 0x33 0x44 0x00 0x00 0x00 0x00 0xC2 0x04 0x01 0x02 0x03 0x04 0x00 0x00 0x00 0x00 0x00 0x00 0x00 0x00 0x7F;\n");
        sb.append("// 00 00 00 00 00 00 00 00 90 00;\n");
        sb.append("// Initialize Parameter Update\n");
        sb.append("0x80 0x24 0x00 0x00 0x00 0x7F;\n");
        sb.append("// 00 00 00 00 0c 1f 63 00 03 90 00 = Purse ID : 0x00000000; ExpDate 12/31/99; PUN 3\n");
        sb.append("// Complete Parameter Update: CAD ID 0x11223344; Set ExpDate 12/31/98\n");
        sb.append("0x80 0x26 0x00 0x00 0x15 0x11 0x22 0x33 0x44 0x00 0x00 0x00 0x00 0xC5 0x03 0x0c 0x1f 0x62 0x00 0x00 0x00 0x00 0x00 0x00 0x00 0x00 0x7F;\n");
        sb.append("// 00 00 00 00 00 00 00 00 90 00;\n");
        sb.append("// Initialize Parameter Update\n");
        sb.append("0x80 0x24 0x00 0x00 0x00 0x7F;\n");
        sb.append("// 00 00 00 00 0c 1f 62 00 04 90 00 = Purse ID : 0x00000000; ExpDate 12/31/98; PUN 4\n");
        sb.append("// Complete Parameter Update: CAD ID 0x11223344; Set Purse ID 0x05050505\n");
        sb.append("0x80 0x26 0x00 0x00 0x16 0x11 0x22 0x33 0x44 0x00 0x00 0x00 0x00 0xC6 0x04 0x05 0x05 0x05 0x05 0x00 0x00 0x00 0x00 0x00 0x00 0x00 0x00 0x7F;\n");
        sb.append("// 00 00 00 00 00 00 00 00 90 00;\n");
        sb.append("// Initialize Parameter Update\n");
        sb.append("0x80 0x24 0x00 0x00 0x00 0x7F;\n");
        sb.append("// 05 05 05 05 0c 1f 62 00 05 90 00 = Purse ID : 0x05050505; ExpDate 12/31/98; PUN 5\n");
        sb.append("// Complete Parameter Update: CAD ID 0x11223344; Set Max Balance $320.00;\n");
        sb.append("0x80 0x26 0x00 0x00 0x14 0x11 0x22 0x33 0x44 0x00 0x00 0x00 0x00 0xC7 0x02 0x7D 0x00 0x00 0x00 0x00 0x00 0x00 0x00 0x00 0x00 0x7F;\n");
        sb.append("// 00 00 00 00 00 00 00 00 90 00;\n");
        sb.append("// Initialize Parameter Update\n");
        sb.append("0x80 0x24 0x00 0x00 0x00 0x7F;\n");
        sb.append("// 05 05 05 05 0c 1f 62 00 06 90 00 = Purse ID : 0x05050505; ExpDate 12/31/98; PUN 6\n");
        sb.append("// Complete Parameter Update: CAD ID 0x11223344; Set Max Transaction $30.00;\n");
        sb.append("0x80 0x26 0x00 0x00 0x14 0x11 0x22 0x33 0x44 0x00 0x00 0x00 0x00 0xC8 0x02 0x0B 0xB8 0x00 0x00 0x00 0x00 0x00 0x00 0x00 0x00 0x7F;\n");
        sb.append("// 00 00 00 00 00 00 00 00 90 00;\n");
        sb.append("// Initialize Parameter Update\n");
        sb.append("0x80 0x24 0x00 0x00 0x00 0x7F;\n");
        sb.append("// 05 05 05 05 0c 1f 62 00 07 90 00 = Purse ID : 0x05050505; ExpDate 12/31/98; PUN 7\n");
        sb.append("// Complete Parameter Update: CAD ID 0x11223344; Set Java Purse Version 2.1.0.1\n");
        sb.append("0x80 0x26 0x00 0x00 0x16 0x11 0x22 0x33 0x44 0x00 0x00 0x00 0x00 0xC9 0x04 0x02 0x01 0x00 0x01 0x00 0x00 0x00 0x00 0x00 0x00 0x00 0x00 0x7F;\n");
        sb.append("// 00 00 00 00 00 00 00 00 90 00;\n");
        sb.append("// Initialize Parameter Update\n");
        sb.append("0x80 0x24 0x00 0x00 0x00 0x7F;\n");
        sb.append("// 05 05 05 05 0c 1f 62 00 08 90 00 = Purse ID : 0x05050505; ExpDate 12/31/98; PUN 8\n");
        sb.append("// Complete Parameter Update: CAD ID 0x11223344; Loyalty1 = \"0xa0,00,00,00,62,03,01,0c,05,01 \"\n");
        sb.append("0x80 0x26 0x00 0x00 0x1E 0x11 0x22 0x33 0x44 0x00 0x00 0x00 0x00 0xCA 0x0C 0x33 0x55 0xA0 0x00 0x00 0x00 0x62 0x03 0x01 0x0C 0x05 0x01 0x00 0x00 0x00 0x00 0x00 0x00 0x00 0x00 0x7F;\n");
        sb.append("// 00 00 00 00 00 00 00 00 90 00;\n");
        sb.append("//////////////////////////////////////////////////////////////////////\n");
        sb.append("// End of initialization session, all values are set up.\n");
        sb.append("//////////////////////////////////////////////////////////////////////\n");
        sb.append("//////////////////////////////////////////////////////////////////////\n");
        sb.append("// Regular  transaction session	 at CAD 22446688 in the Bank\n");
        sb.append("//////////////////////////////////////////////////////////////////////\n");
        sb.append("// Select JavaPurse\n");
        sb.append("0x00 0xa4 0x04 0x00 10 0xa0 0 0 0 0x62 3 1 0xc 2 1 127;\n");
        sb.append("// 90 00 = SW_NO_ERROR\n");
        sb.append("// Verify PIN (User PIN 01020304)\n");
        sb.append("0x00 0x20 0x00 0x82 0x04 0x01 0x02 0x03 0x04 0x00;\n");
        sb.append("// 90 00;\n");
        sb.append("// Initialize Transaction: Credit $250.00 \n");
        sb.append("0x80 0x20 0x01 0x00 0x0a 0x61 0xa8 0x22 0x44 0x66 0x88 0x00 0x00 0x00 0x00 0x7F;\n");
        sb.append("// 05 05 05 05 0c 1f 62 00 00 00 01 00 00 00 00 00 00 00 00 90 00 \n");
        sb.append("//= Purse ID : 0x05050505; ExpDate 12/31/98; TN=1\n");
        sb.append("// Complete Transaction: Date 10/27/97; Time 15:33\n");
        sb.append("0x80 0x22 0x00 0x00 0x0d 0x00 0x00 0x00 0x00 0x00 0x00 0x00 0x00 0x0a 0x1b 0x61 0x0f 0x21 0x7F;\n");
        sb.append("// 61 a8 00 00 00 00 00 00 00 00 90 00	= Purse Balance $250.00;\n");
        sb.append("// Initialize Transaction: Debit $25.00;\n");
        sb.append("0x80 0x20 0x02 0x00 0x0a 0x09 0xc4 0x22 0x44 0x66 0x88 0x00 0x00 0x00 0x00 0x7F;\n");
        sb.append("// 05 05 05 05 0c 1f 62 61 a8 00 02 00 00 00 00 00 00 00 00 90 00;\n");
        sb.append("//= Purse ID : 0x05050505; ExpDate 12/31/98; TN=2\n");
        sb.append("// Complete Transaction: Date 10/27/97; Time 15:35\n");
        sb.append("0x80 0x22 0x00 0x00 0x0d 0x00 0x00 0x00 0x00 0x00 0x00 0x00 0x00 0x0a 0x1b 0x61 0x0f 0x23 0x7F;\n");
        sb.append("// 57 e4 00 00 00 00 00 00 00 00 90 00	= Purse Balance $225.00;\n");
        sb.append("/////////////////////////////////////////////////////////////////////\n");
        sb.append("// Regular  transaction session	 at CAD 33557799 in a store\n");
        sb.append("/////////////////////////////////////////////////////////////////////\n");
        sb.append("// Select JavaPurse\n");
        sb.append("0x00 0xa4 0x04 0x00 10 0xa0 0 0 0 0x62 3 1 0xc 2 1 127;\n");
        sb.append("// 90 00 = SW_NO_ERROR\n");
        sb.append("// Verify PIN (User PIN 01020304)\n");
        sb.append("0x00 0x20 0x00 0x82 0x04 0x01 0x02 0x03 0x04 0x00;\n");
        sb.append("// 90 00;\n");
        sb.append("// Initialize Transaction: Debit $22.95\n");
        sb.append("0x80 0x20 0x02 0x00 0x0a 0x08 0xf7 0x33 0x55 0x77 0x99 0x00 0x00 0x00 0x00 0x7F;\n");
        sb.append("// 05 05 05 05 0c 1f 62 57 e4 00 03 00 00 00 00 00 00 00 00 90 00;\n");
        sb.append("//= Purse ID : 0x05050505; ExpDate 12/31/98; TN=3\n");
        sb.append("// Complete Transaction: Date 10/27/97; Time 17:45\n");
        sb.append("0x80 0x22 0x00 0x00 0x0d 0x00 0x00 0x00 0x00 0x00 0x00 0x00 0x00 0x0a 0x1b 0x61 0x11 0x2d 0x7F;\n");
        sb.append("// 4e ed 00 00 00 00 00 00 00 00 90 00	= Purse Balance $202.05\n");
        sb.append("/////////////////////////////////////////////////////////////////////\n");
        sb.append("// A session with various errors at CAD 33445566 \n");
        sb.append("/////////////////////////////////////////////////////////////////////\n");
        sb.append("// Select JavaPurse\n");
        sb.append("0x00 0xa4 0x04 0x00 10 0xa0 0 0 0 0x62 3 1 0xc 2 1 127;\n");
        sb.append("// 90 00 = SW_NO_ERROR\n");
        sb.append("// Initialize Transaction: Debit $22.95\n");
        sb.append("0x80 0x20 0x02 0x00 0x0a 0x08 0xf7 0x33 0x44 0x55 0x66 0x00 0x00 0x00 0x00 0x7F;\n");
        sb.append("// 69 82 = SW \"Security Status Not Satisfied\" : must present PIN first\n");
        sb.append("// Verify PIN (User PIN 01030507)\n");
        sb.append("0x00 0x20 0x00 0x82 0x04 0x01 0x03 0x05 0x07 0x00;\n");
        sb.append("// 69 c4 = SW_PIN_FAILED, 4 tries remained\n");
        sb.append("// Initialize Transaction: Debit $22.95\n");
        sb.append("0x80 0x20 0x02 0x00 0x0a 0x08 0xf7 0x33 0x44 0x55 0x66 0x00 0x00 0x00 0x00 0x7F;\n");
        sb.append("// 69 82 = SW \"Security Status Not Satisfied\"\n");
        sb.append("// Verify PIN (User PIN 01020304)\n");
        sb.append("0x00 0x20 0x00 0x82 0x04 0x01 0x02 0x03 0x04 0x00;\n");
        sb.append("// 90 00 = SW_NO_ERROR\n");
        sb.append("// Complete Transaction: Date 10/28/97; Time 18:45\n");
        sb.append("0x80 0x22 0x00 0x00 0x0d 0x00 0x00 0x00 0x00 0x00 0x00 0x00 0x00 0x0a 0x1c 0x61 0x12 0x2d 0x7F;\n");
        sb.append("// 91 04 = SW_COMMAND_OUT_OF_SEQUENCE: Complete command should follow valid Initialize\n");
        sb.append("// Initialize Transaction: Debit $22.95\n");
        sb.append("0x80 0x20 0x02 0x00 0x0a 0x08 0xf7 0x33 0x44 0x55 0x66 0x00 0x00 0x00 0x00 0x7F;\n");
        sb.append("// 05 05 05 05 0c 1f 62 4e ed 00 04 00 00 00 00 00 00 00 00 90 00  = TN = 4; Balance = $202.05\n");
        sb.append("// Complete Transaction: Date 10/28/97; Time 18:48\n");
        sb.append("0x80 0x22 0x00 0x00 0x0d 0x11 0x11 0x11 0x11 0x11 0x11 0x11 0x11 0x0a 0x1c 0x61 0x12 0x30 0x7F;\n");
        sb.append("// 91 05 = SW_WRONG_SIGNATURE: This attempt of transaction is recorded in the log\n");
        sb.append("// Complete Transaction: Date 10/28/97; Time 18:50;\n");
        sb.append("0x80 0x22 0x00 0x00 0x0d 0x35 0xa9 0x3b 0x26 0x50 0x58 0x97 0x93 0x0a 0x1c 0x61 0x12 0x32 0x7F;\n");
        sb.append("// 91 04 = SW_COMMAND_OUT_OF_SEQUENCE\n");
        sb.append("// (Transaction with a wrong signature is in a way completed, \n");
        sb.append("// We can't retry with another signature.)\n");
        sb.append("// Initialize transaction: Debit $9.86\n");
        sb.append("0x80 0x20 0x02 0x00 0x0a 0x03 0xda 0x33 0x44 0x55 0x66 0x00 0x00 0x00 0x00 0x7F;\n");
        sb.append("// 05 05 05 05 0c 1f 62 4e ed 00 05 00 00 00 00 00 00 00 00 90 00 = TN = 5; Balance = $202.05\n");
        sb.append("// Complete Transaction: Date 10/28/97; Time 18:53\n");
        sb.append("0x80 0x22 0x00 0x00 0x0d 0x00 0x00 0x00 0x00 0x00 0x00 0x00 0x00 0x0a 0x1c 0x61 0x12 0x35 0x7F;\n");
        sb.append("// 4b 13 00 00 00 00 00 00 00 00 90 00 = Balance = $192.19\n");
        sb.append("// Initialize transaction: Debit $30.01\n");
        sb.append("0x80 0x20 0x02 0x00 0x0a 0x0b 0xb9 0x33 0x44 0x55 0x66 0x00 0x00 0x00 0x00 0x7F;\n");
        sb.append("// 91 03 = SW_AMOUNT_TOO_HIGH (The Max Amount was set to $30.00)\n");
        sb.append("// Initialize transaction: Credit $127.82\n");
        sb.append("0x80 0x20 0x01 0x00 0x0a 0x31 0xee 0x33 0x44 0x55 0x66 0x00 0x00 0x00 0x00 0x7F;\n");
        sb.append("// 91 01 = SW_CREDIT_TOO_HIGH (The Max Balance was set to $320.00, \n");
        sb.append("// this transaction would bring it to 320.01)\n");
        sb.append("/////////////////////////////////////////////////////////////////////\n");
        sb.append("// Session of reading balance and log at CAD 22446688 in the Bank\n");
        sb.append("/////////////////////////////////////////////////////////////////////\n");
        sb.append("// Select JavaPurse\n");
        sb.append("0x00 0xa4 0x04 0x00 10 0xa0 0 0 0 0x62 3 1 0xc 2 1 127;\n");
        sb.append("// 90 00 = SW_NO_ERROR\n");
        sb.append("// Verify PIN (User PIN 01020304)\n");
        sb.append("0x00 0x20 0x00 0x82 0x04 0x01 0x02 0x03 0x04 0x00;\n");
        sb.append("// 90 00;\n");
        sb.append("// Read the only record in Balances file : \n");
        sb.append("// SFI = 4 (00100), record N is specified in P1 => P2 = 00100100 = 0x24\n");
        sb.append("0x00 0xb2 0x01 0x24 0x00 0x7F;\n");
        sb.append("// 4b 13 7d 00 0b b8 90 00 = Balance = $192.19, Max Balance = $320.00, Max Transaction = $30;\n");
        sb.append("// Read the first record in log file\n");
        sb.append("// SFI = 3 (00011), record N is specified in P1 => P2 = 00011100 = 0x1c\n");
        sb.append("0x00 0xb2 0x01 0x1c 0x00 0x7F;\n");
        sb.append("// 00 05 02 03 da 33 44 55 66 0a 1c 61 12 35 4b 13 90 00 90 00 \n");
        sb.append("// TN = 5; Transaction Type = DEBIT(02); Amount = $9.86(03da); CAD ID 33445566;\n");
        sb.append("// Date 10/28/97 (0a 1c 61); Time 18:53(12 35); Balance $192.19 (4b 13), SW = NO_ERROR (9000)\n");
        sb.append("// Read the second record in log file\n");
        sb.append("// SFI = 3 (00011), record N is specified in P1 => P2 = 00011100 = 0x1c\n");
        sb.append("0x00 0xb2 0x02 0x1c 0x00 0x7F;\n");
        sb.append("// 00 04 02 08 f7 33 44 55 66 0a 1c 61 12 30 4e ed 91 05 90 00;\n");
        sb.append("// TN = 4; Transaction Type = DEBIT(02); Amount = $22.95(08f7); CAD ID 33445566;\n");
        sb.append("// Date 10/28/97 (0a 1c 61); Time 18:53(12 35); Balance $202.05 (4eed), SW_WRONG_SIGNATURE (9105)\n");
        sb.append("// Attempt of the transaction is recorded, but balance wasn't change, see next record.\n");
        sb.append("// Read the third record in log file\n");
        sb.append("// SFI = 3 (00011), record N is specified in P1 => P2 = 00011100 = 0x1c\n");
        sb.append("0x00 0xb2 0x03 0x1c 0x00 0x7F;\n");
        sb.append("// 00 03 02 08 f7 33 55 77 99 0a 1b 61 12 2d 4e ed 90 00 90 00 \n");
        sb.append("// TN = 3; Transaction Type = DEBIT(02); Amount = $22.95(08f7); CAD ID 33557799;\n");
        sb.append("// Date 10/27/97 (0a 1b 61); Time 18:45(12 2d); Balance $202.05 (4eed), SW = NO_ERROR (9000)\n");
        sb.append("// Read the fifth record in log file\n");
        sb.append("// SFI = 3 (00011), record N is specified in P1 => P2 = 00011100 = 0x1c\n");
        sb.append("0x00 0xb2 0x05 0x1c 0x00 0x7F;\n");
        sb.append("// 00 01 01 61 a8 22 44 66 88 0a 1b 61 0f 21 61 a8 90 00 90 00;\n");
        sb.append("// TN = 1; Transaction Type = CREDIT(01); Amount = $250.00(61a8); CAD ID 22446688;\n");
        sb.append("// Date 10/27/97 (0a 1b 61); Time 15:33(0f 21); Balance $250.00 (61a8), SW = NO_ERROR (9000)\n");
        sb.append("// Read the sixth record in log file\n");
        sb.append("// SFI = 3 (00011), record N is specified in P1 => P2 = 00011100 = 0x1c\n");
        sb.append("0x00 0xb2 0x06 0x1c 0x00 0x7F;\n");
        sb.append("// 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 90 00 \n");
        sb.append("// Empty record\n");
        sb.append("// Read Expiration Date from Parameters file\n");
        sb.append("// SFI = 2 (00010), record tag 0xc5 is in P1 => P2 = 00010000 = 0x10;\n");
        sb.append("0x00 0xb2 0xc5 0x10 0x00 0x7F;\n");
        sb.append("// 69 82 : SW Security status not satisfied - One has to present Master PIN to read Parameters\n");
        sb.append("// Verify PIN : Master PIN\n");
        sb.append("0x00 0x20 0x00 0x81 0x08 0x01 0x02 0x03 0x04 0x05 0x06 0x07 0x08 0x00;\n");
        sb.append("// 90 00;\n");
        sb.append("// Read Expiration Date from Parameters file\n");
        sb.append("// SFI = 2 (00010), record tag 0xc5 is in P1 => P2 = 00010000 = 0x10;\n");
        sb.append("0x00 0xb2 0xc5 0x10 0x00 0x7F;\n");
        sb.append("// c5 03 0c 1f 62 90 00	  = Tag = 0xc5, Exp. Date = 12/31/98 (0c 1f 62)\n");
        sb.append("// Select File: select EF under current DF (P1 = 0x02); FID = 0x9103\n");
        sb.append("0x00 0xa4 0x02 0x0c 0x02 0x91 0x03 0x00;\n");
        sb.append("// 90 00;\n");
        sb.append("// Read the first record in the selected file\n");
        sb.append("// currently selected file, record N is specified in P1 => P2 = 00000100 = 0x04\n");
        sb.append("0x00 0xb2 0x01 0x04 0x00 0x7F;\n");
        sb.append("// 00 05 02 03 da 33 44 55 66 0a 1c 61 12 35 4b 13 90 00 90 00 \n");
        sb.append("// TN = 5; Transaction Type = DEBIT(02); Amount = $9.86(03da); CAD ID 33445566;\n");
        sb.append("// Date 10/28/97 (0a 1c 61); Time 18:53(12 35); Balance $192.19 (4b 13), SW = NO_ERROR (9000)\n");
        sb.append("/////////////////////////////////////////////////////////////\n");
        sb.append("// Additional JavaPurse tests to increase coverage\n");
        sb.append("/////////////////////////////////////////////////////////////\n");
        sb.append("// Select JavaPurse\n");
        sb.append("0x00 0xa4 0x04 0x00 10 0xa0 0 0 0 0x62 3 1 0xc 2 1 127;\n");
        sb.append("// 90 00 = SW_NO_ERROR\n");
        sb.append("// Verify PIN (User PIN 01020304)\n");
        sb.append("0x00 0x20 0x00 0x82 0x04 0x01 0x02 0x03 0x04 0x00;\n");
        sb.append("// 90 00;\n");
        sb.append("// Wrong INS for Java Purse CLA\n");
        sb.append("0x80 0x34 0x00 0x00 0x00 0x00;\n");
        sb.append("// 6d 00;\n");
        sb.append("// Wrong CLA\n");
        sb.append("0x60 0x00 0x00 0x00 0x00 0x00;\n");
        sb.append("// 6e 00;\n");
        sb.append("// Wrong INS for ISO CLA\n");
        sb.append("0x00 0x74 0x00 0x00 0x00 0x00;\n");
        sb.append("// 6d 00 \n");
        sb.append("// Initialize transaction: wrong transaction type\n");
        sb.append("0x80 0x20 0x03 0x00 0x0a 0x0b 0xb9 0x33 0x44 0x55 0x66 0x00 0x00 0x00 0x00 0x7F;\n");
        sb.append("// 6a 86 == SW_INCORRECT_P1P2\n");
        sb.append("// Initialize transaction: incorrect P2\n");
        sb.append("0x80 0x20 0x02 0x11 0x0a 0x0b 0xb9 0x33 0x44 0x55 0x66 0x00 0x00 0x00 0x00 0x7F;\n");
        sb.append("// 6a 86 == SW_INCORRECT_P1P2\n");
        sb.append("// Initialize transaction: incorrect LC\n");
        sb.append("0x80 0x20 0x02 0x11 0x0b 0x0b 0xb9 0x33 0x44 0x55 0x66 0x00 0x00 0x00 0x00 0x00 0x7F;\n");
        sb.append("// 67 00 == SW_WRONG_LENGTH\n");
        sb.append("// Initialize transaction: Debit $9.86\n");
        sb.append("0x80 0x20 0x02 0x00 0x0a 0x03 0xda 0x33 0x44 0x55 0x66 0x00 0x00 0x00 0x00 0x7F;\n");
        sb.append("// 05 05 05 05 0c 1f 62 4b 13 00 06 00 00 00 00 00 00 00 00 90 00 = TN = 6; Balance = $192.19\n");
        sb.append("// Complete Transaction: incorrect LC\n");
        sb.append("0x80 0x22 0x00 0x00 0x0c 0x00 0x00 0x00 0x00 0x00 0x00 0x00 0x0a 0x1c 0x61 0x12 0x35 0x7F;\n");
        sb.append("// 67 00 == SW_WRONG_LENGTH\n");
        sb.append("// Complete Transaction: incorrect P1\n");
        sb.append("0x80 0x22 0x11 0x00 0x0d 0x00 0x00 0x00 0x00 0x00 0x00 0x00 0x00 0x0a 0x1c 0x61 0x12 0x35 0x7F;\n");
        sb.append("// 6a 86 == SW_INCORRECT_P1P2\n");
        sb.append("// Complete Transaction: incorrect P2\n");
        sb.append("0x80 0x22 0x00 0x11 0x0d 0x00 0x00 0x00 0x00 0x00 0x00 0x00 0x00 0x0a 0x1c 0x61 0x12 0x35 0x7F;\n");
        sb.append("// 6a 86 == SW_INCORRECT_P1P2\n");
        sb.append("/////////////////////////////////////////////////////////\n");
        sb.append("// Additional cases for Parameter Update\n");
        sb.append("/////////////////////////////////////////////////////////\n");
        sb.append("// Select JavaPurse\n");
        sb.append("0x00 0xa4 0x04 0x00 10 0xa0 0 0 0 0x62 3 1 0xc 2 1 127;\n");
        sb.append("// 90 00 = SW_NO_ERROR\n");
        sb.append("// Initialize Parameter Update\n");
        sb.append("0x80 0x24 0x00 0x00 0x00 0x7F; \n");
        sb.append("// 69 82 = SW \"Security Status Not Satisfied\" : must present PIN first\n");
        sb.append("// Verify PIN : Master PIN\n");
        sb.append("0x00 0x20 0x00 0x86 0x08 0x01 0x02 0x03 0x04 0x05 0x06 0x07 0x08 0x00;\n");
        sb.append("// 6a 86 == SW_INCORRECT_P1P2\n");
        sb.append("// Verify PIN : Master PIN\n");
        sb.append("0x00 0x20 0x00 0x81 0x08 0x01 0x02 0x03 0x04 0x05 0x06 0x07 0x08 0x00;\n");
        sb.append("// 90 00;\n");
        sb.append("// Initialize Parameter Update\n");
        sb.append("0x80 0x24 0x11 0x00 0x00 0x7F; \n");
        sb.append("// 6a 86 == SW_INCORRECT_P1P2\n");
        sb.append("// Initialize Parameter Update\n");
        sb.append("0x80 0x24 0x00 0x11 0x00 0x7F; \n");
        sb.append("// 6a 86 == SW_INCORRECT_P1P2\n");
        sb.append("// Initialize Parameter Update\n");
        sb.append("0x80 0x24 0x00 0x00 0x00 0x7F; \n");
        sb.append("// 05 05 05 05 0c 1f 62 00 09 90 00 = Purse ID : 0x05050505; ExpDate 12/31/98; PUN 9 \n");
        sb.append("// Initialize Parameter Update\n");
        sb.append("0x80 0x24 0x00 0x00 0x00 0x7F;\n");
        sb.append("// 91 04 = SW_COMMAND_OUT_OF_SEQUENCE\n");
        sb.append("// Complete Parameter Update: CAD ID 0x11223344; Set ExpDate 12/31/98\n");
        sb.append("0x80 0x26 0x11 0x00 0x15 0x11 0x22 0x33 0x44 0x00 0x00 0x00 0x00 0xC5 0x03 0x0c 0x1f 0x62 0x00 0x00 0x00 0x00 0x00 0x00 0x00 0x00 0x7F;\n");
        sb.append("// 6a 86 == SW_INCORRECT_P1P2\n");
        sb.append("// Complete Parameter Update: CAD ID 0x11223344; Set ExpDate 12/31/98\n");
        sb.append("0x80 0x26 0x00 0x11 0x15 0x11 0x22 0x33 0x44 0x00 0x00 0x00 0x00 0xC5 0x03 0x0c 0x1f 0x62 0x00 0x00 0x00 0x00 0x00 0x00 0x00 0x00 0x7F;\n");
        sb.append("// 6a 86 == SW_INCORRECT_P1P2\n");
        sb.append("// Complete Parameter Update: CAD ID 0x11223344; Set ExpDate 12/31/98\n");
        sb.append("0x80 0x26 0x00 0x00 0x15 0x11 0x22 0x33 0x44 0x00 0x00 0x00 0x00 0xC5 0x03 0x0c 0x1f 0x62 0x00 0x11 0x00 0x11 0x00 0x11 0x00 0x11 0x7F;\n");
        sb.append("// 91 05 == SW_WRONG_SIGNATURE\n");
        sb.append("// Complete Parameter Update: CAD ID 0x11223344; Set ExpDate 12/31/98\n");
        sb.append("0x80 0x26 0x00 0x00 0x15 0x11 0x22 0x33 0x44 0x00 0x00 0x00 0x00 0xCF 0x03 0x0c 0x1f 0x62 0x00 0x00 0x00 0x00 0x00 0x00 0x00 0x00 0x7F;\n");
        sb.append("// 6a 81 == SW_FUNC_NOT_SUPPORTED\n");
        sb.append("// Complete Parameter Update: CAD ID 0x11223344; Set ExpDate 12/31/98\n");
        sb.append("0x80 0x26 0x00 0x00 0x15 0x11 0x22 0x33 0x44 0x00 0x00 0x00 0x00 0xC5 0x03 0x0c 0x1f 0x62 0x00 0x00 0x00 0x00 0x00 0x00 0x00 0x00 0x7F;\n");
        sb.append("// 00 00 00 00 00 00 00 00 90 00;\n");
        sb.append("// Complete Parameter Update: CAD ID 0x11223344; Set ExpDate 12/31/98\n");
        sb.append("0x80 0x26 0x00 0x00 0x15 0x11 0x22 0x33 0x44 0x00 0x00 0x00 0x00 0xC5 0x03 0x0c 0x1f 0x62 0x00 0x00 0x00 0x00 0x00 0x00 0x00 0x00 0x7F;\n");
        sb.append("// 91 04 = SW_COMMAND_OUT_OF_SEQUENCE\n");
        sb.append("// Initialize Parameter Update\n");
        sb.append("0x80 0x24 0x00 0x00 0x00 0x7F;\n");
        sb.append("// 05 05 05 05 0c 1f 62 00 0A 90 00 = Purse ID : 0x05050505; ExpDate 12/31/98; PUN 10\n");
        sb.append("// Complete Parameter Update: CAD ID 0x11223344; Set Max Transaction $200.00;\n");
        sb.append("0x80 0x26 0x00 0x00 0x14 0x11 0x22 0x33 0x44 0x00 0x00 0x00 0x00 0xC8 0x02 0x4E 0x20 0x00 0x00 0x00 0x00 0x00 0x00 0x00 0x00 0x7F;\n");
        sb.append("// 00 00 00 00 00 00 00 00 90 00;\n");
        sb.append("// Select JavaPurse\n");
        sb.append("0x00 0xa4 0x04 0x00 10 0xa0 0 0 0 0x62 3 1 0xc 2 1 127;\n");
        sb.append("// 90 00 = SW_NO_ERROR\n");
        sb.append("// Verify PIN (User PIN 01020304)\n");
        sb.append("0x00 0x20 0x00 0x82 0x04 0x01 0x02 0x03 0x04 0x00;\n");
        sb.append("// 90 00;\n");
        sb.append("// Initialize transaction: Debit $199.99\n");
        sb.append("0x80 0x20 0x02 0x00 0x0a 0x4E 0x1F 0x33 0x44 0x55 0x66 0x00 0x00 0x00 0x00 0x7F;\n");
        sb.append("// 91 02 == SW_NOT_ENOUGH_FUNDS\n");
        sb.append("// Initialize transaction: Debit $192.19\n");
        sb.append("0x80 0x20 0x02 0x00 0x0a 0x4b 0x13 0x33 0x44 0x55 0x66 0x00 0x00 0x00 0x00 0x7F;\n");
        sb.append("// 05 05 05 05 0c 1f 62 4b 13 00 05 00 00 00 00 00 00 00 00 90 00;\n");
        sb.append("// Complete Transaction: Date 10/28/97; Time 18:53\n");
        sb.append("0x80 0x22 0x00 0x00 0x0d 0x00 0x00 0x00 0x00 0x00 0x00 0x00 0x00 0x0a 0x1c 0x61 0x12 0x35 0x7F;\n");
        sb.append("// 00 00 00 00 00 00 00 00 00 00 90 00 = Balance = $0;\n");
        sb.append("/////////////////////////////////////////////////////////////////////\n");
        sb.append("// Select JavaLoyalty\n");
        sb.append("/////////////////////////////////////////////////////////////////////\n");
        sb.append("0x00 0xa4 0x04 0x00 10 0xa0 0 0 0 0x62 3 1 0xc 5 1 127;\n");
        sb.append("// 90 00 = SW_NO_ERROR\n");
        sb.append("// Read Balance\n");
        sb.append("0x90 0x20 0x00 0x00 0x00 0x00;\n");
        sb.append("// 00 16 90 00 = Balance=22 points\n");
        sb.append("// Reset Balance\n");
        sb.append("0x90 0x22 0x00 0x00 0x00 0x00;\n");
        sb.append("// 90 00 = SW_NO_ERROR\n");
        sb.append("/////////////////////////////////////////////////////////////////////\n");
        sb.append("// Initialize Wallet\n");
        sb.append("/////////////////////////////////////////////////////////////////////\n");
        sb.append("//Select Wallet\n");
        sb.append("0x00 0xA4 0x04 0x00 0x0a 0xa0 0x0 0x0 0x0 0x62 0x3 0x1 0xc 0x6 0x1 0x7F;\n");
        sb.append("// 90 00 = SW_NO_ERROR\n");
        sb.append("//Verify user pin\n");
        sb.append("0x80 0x20 0x00 0x00 0x05 0x01 0x02 0x03 0x04 0x05 0x7F;\n");
        sb.append("//90 00 = SW_NO_ERROR\n");
        sb.append("//Get wallet balance\n");
        sb.append("0x80 0x50 0x00 0x00 0x00 0x02;\n");
        sb.append("//0x00 0x00 0x00 0x00 0x90 0x00 = Balance = 0 and SW_ON_ERROR\n");
        sb.append("//Attemp to debit from an empty account\n");
        sb.append("0x80 0x40 0x00 0x00 0x01 0x64 0x7F; \n");
        sb.append("//0x6A85 = SW_NEGATIVE_BALANCE\n");
        sb.append("//Credit $100 to the empty account\n");
        sb.append("0x80 0x30 0x00 0x00 0x01 0x64 0x7F; \n");
        sb.append("//0x9000 = SW_NO_ERROR\n");
        sb.append("//Get Balance\n");
        sb.append("0x80 0x50 0x00 0x00 0x00 0x02;\n");
        sb.append("//0x00 0x64 0x9000 = Balance = 100 and SW_NO_ERROR\n");
        sb.append("//Debit $50 from the account\n");
        sb.append("0x80 0x40 0x00 0x00 0x01 0x32 0x7F; \n");
        sb.append("//0x9000 = SW_NO_ERROR\n");
        sb.append("//Get Balance\n");
        sb.append("0x80 0x50 0x00 0x00 0x00 0x02;\n");
        sb.append("//0x00 0x32 0x9000 = Balance = 50 and SW_NO_ERROR\n");
        sb.append("//Credit $128 to the account\n");
        sb.append("0x80 0x30 0x00 0x00 0x01 0x80 0x7F; \n");
        sb.append("//0x6A83 = SW_INVALID_TRANSACTION_AMOUNT\n");
        sb.append("//Get Balance\n");
        sb.append("0x80 0x50 0x00 0x00 0x00 0x02;\n");
        sb.append("//0x00 0x32 0x9000 = Balance = 50 and SW_NO_ERROR\n");
        sb.append("//Debit $51 from the account\n");
        sb.append("0x80 0x40 0x00 0x00 0x01 0x33 0x7F;\n");
        sb.append("//0x6A85 = SW_NEGATIVE_BALANC\n");
        sb.append("//Get Balance\n");
        sb.append("0x80 0x50 0x00 0x00 0x00 0x02;\n");
        sb.append("//0x00 0x32 0x9000 = Balance = 50 and SW_NO_ERROR\n");
        sb.append("//Debit $128 from the account\n");
        sb.append("0x80 0x40 0x00 0x00 0x01 0x80 0x7F;\n");
        sb.append("//0x6A83 = SW_INVALID_TRANSACTION_AMOUNT\n");
        sb.append("//Get Balance\n");
        sb.append("0x80 0x50 0x00 0x00 0x00 0x02;\n");
        sb.append("//0x00 0x32 0x9000 = Balance = 50 and SW_NO_ERROR\n");
        sb.append("//Reselect Wallet applet so that userpin is reset\n");
        sb.append("0x00 0xA4 0x04 0x00 0x0a 0xa0 0x0 0x0 0x0 0x62 0x3 0x1 0xc 0x6 0x1 0x7F;\n");
        sb.append("// 90 00 = SW_NO_ERROR\n");
        sb.append("//Credit $127 to the account before pin verification\n");
        sb.append("0x80 0x30 0x00 0x00 0x01 0x7F 0x7F;\n");
        sb.append("//0x6301 = SW_PIN_VERIFICATION_REQUIRED\n");
        sb.append("//Verify User pin with wrong pin value\n");
        sb.append("0x80 0x20 0x00 0x00 0x04 0x01 0x03 0x02 0x66 0x7F;\n");
        sb.append("//0x6300 = SW_VERIFICATION_FAILED\n");
        sb.append("//Verify user pin again with correct pin value \n");
        sb.append("//0x80 0x20 0x00 0x00 0x08 0xF2 0x34 0x12 0x34 0x56 0x10 0x01 0x01 0x7F;\n");
        sb.append("0x80 0x20 0x00 0x00 0x05 0x01 0x02 0x03 0x04 0x05 0x7F;\n");
        sb.append("//0x9000 = SW_NO_ERROR\n");
        sb.append("//Get balance with incorrrect LE value \n");
        sb.append("0x80 0x50 0x00 0x00 0x00 0x01;\n");
        sb.append("//0x6700 = ISO7816.SW_WRONG_LENGTH\n");
        sb.append("//Get balance \n");
        sb.append("0x80 0x50 0x00 0x00 0x00 0x02;\n");
        sb.append("//0x00 0x32 0x9000 = Balance = 50 and SW_NO_ERROR\n");
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
