package rtrCfg;

/*
package name in camelCase
file name in capscaps format(no underscore)
class variable should start with c<underscore>varName  c_camelCase
Use prefix/sufix combo of understandable names. ex: Router - Rtr, Error - Err, Table - Tbl
Fn Name also camelCase
class instance should be stored like c_<className>
 */

import arp.HwArpTable;
import fib.FibTable;
import rtrTblUtils.V4Utils;

public class RtrHdlr {

    public int MAX_ROUTERS = 6;
    public FibTable[] c_fibTable;
    public short[] c_portMap;
    public V4Utils c_v4Utils;
    public HwArpTable c_hwArpTable;

    public RtrHdlr() {
        c_portMap = new short[6];
        c_fibTable = new FibTable[MAX_ROUTERS];
        for(int i=0;i<MAX_ROUTERS;i++) {
            c_fibTable[i] = new FibTable();
        }
        c_v4Utils = new V4Utils();
        c_hwArpTable = new HwArpTable();
    }


    public static byte mapIntfIdToRouterId(byte intf_id) {
        return (byte)(intf_id/2);   // test this
    }
    private byte convertToPortNum(byte router_id, byte port_id) {
        switch (router_id) {
            case 0:
                switch (port_id) {
                    case 1:
                        return 0;
                    case 2:
                        return 1;
                }
                break;
            case 1:
                switch (port_id) {
                    case 1:
                        return 2;
                    case 2:
                        return 3;
                }
                break;
            case 2:
                switch (port_id) {
                    case 1:
                        return 4;
                    case 2:
                        return 5;
                }
                break;
            case 3:
                switch (port_id) {
                    case 1:
                        return 6;
                    case 2:
                        return 7;
                }
                break;
            case 4:
                switch (port_id) {
                    case 1:
                        return 8;
                    case 2:
                        return 9;
                }
                break;
            case 5:
                switch (port_id) {
                    case 1:
                        return 10;
                    case 2:
                        return 11;
                }
                break;
        }
        return -1;
    }

    public void setConnection(byte source_rid, byte dest_rid, byte port_id) {
        c_portMap[source_rid] = (short) ((short) c_portMap[source_rid] | (1 << convertToPortNum(dest_rid, port_id)));
    }

    public short isConnected(byte source_rid, byte dest_rid, byte port_id) {
        return (short) ((c_portMap[source_rid]) & (1 << (convertToPortNum(dest_rid,port_id))));
    }

    public void deleteConnection(byte source_rid, byte dest_rid, byte port_id) {
        short temp = 0;
        temp = (short) (temp | (1 << convertToPortNum(dest_rid,port_id)));
        c_portMap[source_rid] = (short) (c_portMap[source_rid] & ~temp);
    }

    public byte getSelfIntfIdfromRtrId(byte router_id) {
        return (byte)(router_id*2);
    }
    /*public static void main(String[] args) {
        RouterHandler rh = new RouterHandler();
        rh.setConnection((byte) 1, (byte) 2, (byte) 1);
        rh.setConnection((byte) 1, (byte) 3, (byte) 2);
        rh.setConnection((byte) 1, (byte) 4, (byte) 1);
        rh.setConnection((byte) 1, (byte) 5, (byte) 2);
        System.out.println(rh.isConnected((byte)1,(byte) 3,(byte) 1));
        System.out.println(rh.isConnected((byte)1,(byte) 3,(byte) 2));
        System.out.println(rh.isConnected((byte)1,(byte) 2,(byte) 2));
        System.out.println(rh.isConnected((byte)1,(byte) 2,(byte) 1));
        for(int i=0;i<6;i++) {
            System.out.print(rh.c_portMap[i]+" ");
        }
        System.out.println();
        rh.deleteConnection((byte) 1, (byte) 4, (byte) 1);
        for(int i=0;i<6;i++) {
            System.out.print(rh.c_portMap[i]+" ");
        }
//        System.out.println("Is connected from 1 -> 2 at line 1 : " + rh.isConnected((byte)1, (byte)2, (byte)1));
//        System.out.println("Is connected from 2 -> 1 at line 1 : " + rh.isConnected((byte)2, (byte)1, (byte)1));
    }*/
}

