package ru.mentee.power.solid.isp.task.solution.client;

import ru.mentee.power.solid.isp.task.solution.interf.Printable;

public class RefactoredPrintClient {
    public void sendToPrint(Printable device, String doc) {
        System.out.println("RefactoredPrintClient: Отправка на печать...");
        device.print(doc);
    }
}
