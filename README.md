## Как запустить?

1. Спуллить себе проект с github
```shell
git pull https://github.com/EdwinShadian/java-db.git
```
2. Перейти в директорию проекта:
```shell
cd ./java-db
```
3. Запустить docker-compose:
```shell
docker-compose up -d
```
4. Восстановить базу из sql дампа:
```shell
docker exec -it mysql mysql -u root --password=root -v < /mnt/storage/dump.sql
```
5. Можно пробовать комманды из src/main/cmd/console
   1. Для первого задания не нужны аргументы
   2. Для второго задания нужно передать villain id первым аргументом
   3. Для третьего задания нужно передать аргументы в следующем порядке:
      1. minion name
      2. minion age
      3. town
      4. villain
   4. Для четвертого задания нужно передать первым аргументом villain id