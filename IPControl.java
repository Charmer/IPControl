public class IPControl extends Applet {
 
    private String IpLog = new String();
    private String MacLog = new String();
 
    public String getMacLog() {
        return MacLog;
    }
 
    public String getIpLog() {
        return this.IpLog;
    }
 
    private void setIpLog() {
        String regexp = "^[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}$";
        String IP;
        String Mac;
        boolean IsValid;
        try {
            Enumeration n = NetworkInterface.getNetworkInterfaces();
            for (; n.hasMoreElements();) {
                NetworkInterface e = n.nextElement();
                Enumeration a = e.getInetAddresses();
                for (; a.hasMoreElements();) {
                    InetAddress addr = a.nextElement();
                    IP = addr.getHostAddress();
                    IsValid = Pattern.matches(regexp, IP);
                    if (IsValid) {
                        byte m[] = e.getHardwareAddress();
                        StringBuilder sb = new StringBuilder(18);
                        for (byte b : m) {
                            if (sb.length() &gt; 0)
                                sb.append('-');
                            sb.append(String.format("%02x", b));
                        }
                        Mac = sb.toString();
                        if(IP.equals("127.0.0.1")) continue;
                        this.IpLog += "IP:" + IP + ",";
                        this.MacLog += "MAC:" + Mac + ",";
                    }
                }
            }
        } catch (SocketException ex) {
            Logger.getLogger(IPControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 
    public void init()
    {
        setIpLog();
    }
}