Если ваша операционная система Windows, то все команды необходимо выполнять в Git Bash

1. Выполнить подключение к серверу (<user> - root, <host> - IP адресс сервера) :
   ssh <user>@<host>
   После подключения к серверу, команды будут выполняться на нем

2. Выполнить команду, которая выведет в главную дирректорию:
   pwd 
   После исполнения команды, должно быть выведено /root

3. Создать новую директорию:
   mkdir <folder-name>
   Проверить что директория была создана - ls

4. Обновить зависимости системы:
   sudo apt update

5. Установить Java:
   sudo apt-get install default-jdk Проверить что Java была установлена, можно командой - java

6. Открыть sshd_config:
   sudo nano /etc/ssh/sshd_config

7. Вставить строку над - #Kerberos options, сохранить Ctrl+S и выйти Ctrl+X:
   KexAlgorithms curve25519-sha256@libssh.org,ecdh-sha2-nistp256,ecdh-sha2-nistp384,ecdh-sha2-nistp521,diffie-hellman-group-exchange-sha256,diffie-hellman-group14-sha1,diffie-hellman-group-exchange-sha1,diffie-hellman-group1-sha1

8. Перезагрузить sshd сервис:
   sudo systemctl restart sshd

9. Создать systemd сервис, который будет запускать Ktor сервер при любой перезагрузке:
   sudo nano /etc/systemd/system/<name>.service

10. Вставить конфигурацию в сервис (где <folder-name> - ранее созданная папка, <name> - имя запакованного сервера), сохранить Ctrl+S и выйти Ctrl+X:
   [Unit]
   Description=Auth Service
   After=network.target
   StartLimitIntervalSec=10
   StartLimitBurst=5

   [Service]
   Type=simple
   Restart=always
   RestartSec=1
   User=root
   EnvironmentFile=/etc/environment
   ExecStart=/usr/lib/jvm/default-java/bin/java -jar /root/<folder-name>/<name>.jar

   [Install]
   WantedBy=multi-user.target

11. Запустить сервис:
    sudo systemctl start <name>

12. Создать symlink который автобатически будет запускать сервис при перезагрузке:
    sudo systemctl enable <name>

13. Make sure, your ports are open and you forward the traffic from the standard HTTP port to 8080:
    sudo iptables -t nat -I PREROUTING -p tcp --dport 80 -j REDIRECT --to-ports 8080
    sudo iptables -A INPUT -p tcp --dport 80 -j ACCEPT
    sudo iptables -A INPUT -p tcp --dport 8080 -j ACCEPT

14. Сохранить правила iptables:
    sudo apt-get install iptables-persistent

15. Добавить JWT_SECRET и MONGO_PW в переменные окружения:
    sudo nano /etc/environment
    JWT_SECRET=<your-secret>
    MONGO_PW=<your-mongo-db-pw>

16. Выйти из сервера:
    exit

17. Создать пару ssh:
    ssh-keygen -m PEM -t rsa
    /Users/<user-name>/.ssh/<name>

18. Перейти в директорию .ssh
    cd /Users/alex/.ssh

19. Скопировать ssh на сервер:
    ssh-copy-id -i <keyname> <user>@<host>

20. Войти на сервер через SSH, без ввода пароля:
    ssh -i <keyname> <user>@<host>

21. Создать директорию keys в корне проекта и поместить туда private-ssh

