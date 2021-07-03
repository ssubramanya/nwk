package fib;
import java.util.Map;

import rtrCfg.RtrHdlr;

import java.util.HashMap;

public class FibTable {
    public HashMap<Long,FibStructure> fibmap;
    public FibTable() {
         fibmap =  new HashMap<Long,FibStructure>();
    }

    public short V4_ADDR_SIZE = 32;
    public FibStructure fibLookup(Long v4Addr) {
        long temp;
        short tempSize;
        short maxMatch = -1;
        FibStructure qualifiedEntry = null;
        for(Map.Entry<Long, FibStructure> fibItr : fibmap.entrySet()) {
            temp = fibItr.getKey() ^ v4Addr;
            tempSize = (short)((long)V4_ADDR_SIZE - Long.toBinaryString(temp).length());
            if ((tempSize > fibItr.getValue().c_v4Address.c_subnet) && maxMatch < tempSize) {
                maxMatch = tempSize;
                qualifiedEntry = fibItr.getValue();
            }
        }
      //  qualifiedEntry = new FibStructure(null, (byte)-2,0L);
        return qualifiedEntry;
    }

    public void displayFibEntries(RtrHdlr rh) {
        for(Map.Entry<Long, FibStructure> fibItr : fibmap.entrySet()) {
            System.out.print("IP : " + rh.c_v4Utils.ipToString(fibItr.getKey()) + "/" + fibItr.getValue().c_v4Address.c_subnet+"       ");
            System.out.print("Outgoing Intf : " + fibItr.getValue().c_intf_id+"        ");
            System.out.println("Nexthop IP : " + rh.c_v4Utils.ipToString(fibItr.getValue().c_nexthop));
        }
    }

}
