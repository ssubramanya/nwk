package fib;

import rtrTblUtils.V4Adrress;

public class FibStructure {

    public V4Adrress c_v4Address;
    public byte c_intf_id;
    public long c_nexthop;

    public FibStructure(V4Adrress v4Address, byte intf_id, long nexthop) {
        this.c_v4Address = v4Address;
        this.c_intf_id = intf_id;
        this.c_nexthop = nexthop;
    }

}
