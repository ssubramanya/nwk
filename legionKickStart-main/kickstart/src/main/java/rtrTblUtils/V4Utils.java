package rtrTblUtils;

public class V4Utils {

    public String ipToString(long v4_addr) {
        StringBuilder ip =  new StringBuilder();
        for(int i=0; i<4; i++) {
            ip.insert(0, Long.toString(v4_addr&0xff));
            if(i < 3) {
                ip.insert(0,'.');
            }
            v4_addr = v4_addr >> 8;
        }
        return ip.toString();
    }

    public long ipToDecimal(String v4_addr) {
        long ipDecimal = 0;
        String[] ipAddressInArray = v4_addr.split("\\.");
        for (int i = 3; i >= 0; i--) {
            long ip = Long.parseLong(ipAddressInArray[3 - i]);
            ipDecimal |= ip << (i * 8);
        }
        return ipDecimal;
    }
}
