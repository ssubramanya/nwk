package rtrCfg;

public class RtrErrHdlr {
    public enum errorInfo {
        NO_ERROR,
        MAX_UNIT_ERROR;
    }

    errorInfo c_errInfo;
    public RtrErrHdlr(){

    };

    String errorNumbertoInfo(errorInfo errno) {
        switch (errno) {
            case MAX_UNIT_ERROR:
                //push this onto the screen;
                return "Maximum allowed logical units to configure is 4";

        }
        return "";
    }

}
