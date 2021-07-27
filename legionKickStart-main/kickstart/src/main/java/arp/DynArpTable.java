package arp;

import fib.FibStructure;
import rtrCfg.RtrErrHdlr;

import java.util.HashMap;
import java.util.Map;

public class DynArpTable {

    public HashMap<Long, Long> c_arpTbl;

    DynArpTable() {
        c_arpTbl = new HashMap<Long, Long>();
    }

    public void insertArpEntry(long ipAddr, long macAddr) {
        c_arpTbl.put(ipAddr, macAddr);
    }

    public void displayArpTbl() {
        for (Map.Entry<Long, Long> arpItr : c_arpTbl.entrySet()) {
            System.out.print(arpItr.getKey() + "    " + arpItr.getValue() + "\n");
        }
    }

    public void removeArpEntry(long ipAddr) {
        c_arpTbl.remove(ipAddr);
    }

    public RtrErrHdlr.errorInfo resolveArp(long ipAddr) {

        return RtrErrHdlr.errorInfo.NO_ERROR;
    }
}
