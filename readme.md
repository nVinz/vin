## # REST-СЕРВИС ПО ГЕНЕРАЦИИ VIN-НОМЕРА
### Подготовка
Для компиляции и сборки проекта из исходников необходим [maven](https://maven.apache.org/ "maven"), для запуска – Java.
### Запуск
Сервис запускается из консоли в директории, где находится готовый .jar файл.
Синтаксис:
`java –jar <Имя_Файла> <IP> <Порт>`
- `<IP>` - IP-адрес, на котором будет работать сервис, по умолчанию 127.0.0.1 (локальный).
- `<Порт>` - Порт, который будет использовать сервис, по умолчанию 8080. Порт не должен быть занят другой программой или службой, а так же открыт для внешнего доступа если планируется использовать сервис удаленно.
Пример: 
`java -jar XAKATOH-1.0-SNAPSHOT.jar 127.0.0.1 8080`
### Получение VIN
После запуска сервиса при запросе /getVIN (`<IP>:<Порт>/getVIN) на заданный IP-адрес появится сгенерированный VIN.
