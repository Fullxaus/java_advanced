package ru.mentee.power.solid.isp.task.solution.client;

import ru.mentee.power.solid.isp.task.solution.interf.Scannable;

public class RefactoredScanClient {
    public void scanDocument(Scannable device, String doc) {
        System.out.println("RefactoredScanClient: Отправка на сканирование...");
        device.scan(doc);
    }
}

