package rtrCfg;


import fib.FibStructure;
import rtrTblUtils.V4Adrress;


public class RtrIntf {

    public class r_unit {
        r_unit(long v4Addr, byte mask) {
            V4Adrress v4Address = new V4Adrress(v4Addr, mask);
        }
    }
    public int MAX_UNIT = 3;
    public byte MAX_MASK = 32;

    r_unit[] c_intfUnit;

    /* Handle subinterfaces
    private RtrErrHdlr.errorInfo create_sub_intf(RtrHdlr rh, RtrIntf r_intf, byte router_id, byte port_id, byte unit_id, long v4Addr, byte mask, boolean isUnitConfigured) {
        if(unit_id > MAX_UNIT) {
            return RtrErrHdlr.errorInfo.MAX_UNIT_ERROR;
        }
        if(isUnitConfigured) {
            //hyIntfAddr = new v4_address(v4Addr,mask);
            //FibStructure fibStruct = new FibStructure(phyIntfAddr,port_id,v4Addr);
            //rh.fibTable[router_id].fibmap.put(v4Addr,fibStruct);
        }
        else {
            r_intf.c_intfUnit[unit_id] = new r_unit(v4Addr, mask);
            return RtrErrHdlr.errorInfo.NO_ERROR;
        }
        return RtrErrHdlr.errorInfo.NO_ERROR;
    }
    */
    public long getNwkAddrforIntfAddr(long v4Addr, byte mask) {
        v4Addr = v4Addr >> mask;
        v4Addr = v4Addr << mask;
        return v4Addr;
    }

    public RtrIntf(RtrHdlr rh, byte router_id, byte intf_id, boolean isunitConfigured, long v4Addr, byte mask) {
        rh.setConnection(router_id, rh.mapIntfIdToRouterId(intf_id), (byte) ((byte)(intf_id % 2)+1));
        for(int i=0;i<6;i++) {
            System.out.print(rh.c_portMap[i]+" ");
        }
        if(!isunitConfigured) {
            V4Adrress phyIntfAddr,phyIntfNwkAddr;
            long v4NwkAddr;
            phyIntfAddr = new V4Adrress(v4Addr,MAX_MASK);
            FibStructure fibStruct = new FibStructure(phyIntfAddr,rh.getSelfIntfIdfromRtrId(router_id),v4Addr);
            rh.c_fibTable[router_id].insertFibEntries(v4Addr,fibStruct);
            v4NwkAddr = getNwkAddrforIntfAddr(v4Addr,(byte)(MAX_MASK - mask));
            phyIntfNwkAddr = new V4Adrress(v4NwkAddr,mask);
            fibStruct = new FibStructure(phyIntfNwkAddr,intf_id,v4Addr);
            rh.c_fibTable[router_id].insertFibEntries(v4NwkAddr,fibStruct);
        }
        else {
            c_intfUnit = new r_unit[MAX_UNIT];
        }
    }

    public static void main(String[] args) {
       // Examples of utility functions  written
        RtrHdlr rh = new RtrHdlr();
        RtrErrHdlr re = new RtrErrHdlr();
       /* RtrIntf ri = new RtrIntf(rh, (byte) 1, (byte) 1, false, rh.c_v4Utils.ipToDecimal("10.0.0.1"), (byte)24);
        /*System.out.println();
        RtrIntf ri2 = new RtrIntf(rh, (byte) 1, (byte) 2,false, rh.c_v4Utils.ipToDecimal("20.0.0.1"), (byte)20);
        System.out.println();
        RtrIntf ri3 = new RtrIntf(rh, (byte) 2, (byte) 5, false, rh.c_v4Utils.ipToDecimal("10.0.128.1"), (byte)27);
        System.out.println();
        //R_Interface ri4 = new R_Interface(rh, (byte) 1, (byte) 4, false, 0L, (byte)0);
        System.out.println();
        //R_Interface ri4 = new R_Interface(rh, (byte) 2, (byte) 1, false);
        System.out.println();
        System.out.println(rh.isConnected((byte)2, (byte)0, (byte) 1));
        rh.deleteConnection((byte)2, (byte)0, (byte) 1);
        System.out.println(rh.isConnected((byte)2, (byte)0, (byte) 1));
        FibStructure fs = rh.fibTable[1].fibLookup(rh.c_v4Utils.ipToDecimal("10.0.0.2"));
        System.out.println(fs);
        System.out.println(rh.map_intfId_to_routerId(fs.c_intf_id));
        System.out.println(rh.c_v4Utils.ipToString(fs.c_v4Address.c_v4Addr));
        System.out.println(fs.c_intf_id);
        System.out.println(rh.c_v4Utils.ipToString(fs.c_nexthop));
        rh.fibTable[1].displayFibEntries(rh);
        rh.fibTable[2].displayFibEntries(rh);
        */
        for(int i=0;i<6;i++) {
            for(int j=0;j<12;j++) {
                System.out.print((Long.toHexString(rh.c_hwArpTable.c_hwTable[i][j]) + " "));
            }
            System.out.println();
        }
    }
}