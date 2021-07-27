package arp;

import java.util.HashMap;

public class HwArpTable {
    /*
        Defining this again here because I don't know to create
        .h file in java to include all the common includes there
     */
    private static final int MAX_ROUTERS = 6;
    private static final int MAX_PORTS = 12;

    public long[][] c_hwTable = new long[MAX_ROUTERS][MAX_PORTS];
    /*
    Storage of mac table in 2d array -- will look like this
    aabbccdd0000 aabbccdd0001 aabbccdd0002 aabbccdd0003 aabbccdd0004 aabbccdd0005 aabbccdd0006 aabbccdd0007 aabbccdd0008 aabbccdd0009 aabbccdd000a aabbccdd000b
    aabbccdd0100 aabbccdd0101 aabbccdd0102 aabbccdd0103 aabbccdd0104 aabbccdd0105 aabbccdd0106 aabbccdd0107 aabbccdd0108 aabbccdd0109 aabbccdd010a aabbccdd010b
    aabbccdd0200 aabbccdd0201 aabbccdd0202 aabbccdd0203 aabbccdd0204 aabbccdd0205 aabbccdd0206 aabbccdd0207 aabbccdd0208 aabbccdd0209 aabbccdd020a aabbccdd020b
    aabbccdd0300 aabbccdd0301 aabbccdd0302 aabbccdd0303 aabbccdd0304 aabbccdd0305 aabbccdd0306 aabbccdd0307 aabbccdd0308 aabbccdd0309 aabbccdd030a aabbccdd030b
    aabbccdd0400 aabbccdd0401 aabbccdd0402 aabbccdd0403 aabbccdd0404 aabbccdd0405 aabbccdd0406 aabbccdd0407 aabbccdd0408 aabbccdd0409 aabbccdd040a aabbccdd040b
    aabbccdd0500 aabbccdd0501 aabbccdd0502 aabbccdd0503 aabbccdd0504 aabbccdd0505 aabbccdd0506 aabbccdd0507 aabbccdd0508 aabbccdd0509 aabbccdd050a aabbccdd050b
    */
    public HwArpTable() {
        for(int i=0; i < MAX_ROUTERS; i++) {
            for(int j=0; j < MAX_PORTS; j++) {
                long arp_entry = 0xAABBCCDD0000L;
                c_hwTable[i][j] = (arp_entry | (((i << 8) | j)));
                /* //map way of storing
                long arp_entry = 0xAABBCCDD0000L;
                int port_entry = ((i << 8) | j);
                arp_entry = arp_entry | port_entry;
                c_hwArpTable.put(port_entry, arp_entry);
                 */
            }
        }
    }
}
