import business.MonitorManager;

public class MainProgram {
    public static void main(String[] args) {
		MonitorManager monitorManager = new MonitorManager();
		monitorManager.createMonitor(12,"Pitillo", "fumatas", true);
		System.out.println(monitorManager.getAllMonitors());
		monitorManager.updateMonitor(12, "notanpitillo", "antifumatas", false);
		System.out.println(monitorManager.getAllMonitors());
		monitorManager.deleteMonitor(12);
		System.out.println(monitorManager.getAllMonitors());		
	}
}
