import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.LinkedList;
import java.util.List;

public class Programm {
    public static void main(String[] args) throws IOException {
        File file = new File();
    }
    static class File{
        //Класс path необходим для задания пути файла и основные первичные функции для работы с файлами, например, чтение из файла и запись всех строк в список - функция readAllLines
        Path result = Path.of("C:\\Users\\maxim\\IdeaProjects\\RepeatedKeywords\\untitled\\src\\result.txt");
        File() throws IOException {
            //Перед обработкой файла очищаем результирующий файл от мусора
            Files.write(result, ("").getBytes());
            Path read = Path.of("C:\\Users\\maxim\\IdeaProjects\\RepeatedKeywords\\untitled\\src\\file.txt");
            //Создаем связанный список с информационной частью - строкой
            List<String> list = new LinkedList<>();
            list = Files.readAllLines(read);
            SplitModules(list);

        }

        private void SplitModules(List<String> list) throws IOException {
            int size = list.size(), i=0;
            List<String> temp_list = new LinkedList<>();
            //Пока весь исходный файл не будет обработан
            while(i!=size){
                //функция get связанного списка выдает true или false по значению получаемого параметра
                if (list.get(i).equals("EOF")) {
                    Uniq(temp_list);
                    //Очищаем временный список от мусора
                    temp_list.clear();
                }
                else temp_list.add(list.get(i));
                i++;
            }
        }
        //метод для поиска уникальных ключей и подсчета дубликатов
        private void Uniq(List <String> list) throws IOException {
            int temp, j=0, k;
            //Поиск реализуется через два цикла, один из которых встроен в другой
            //Схема поиска изображена в методичке к варианту программы на C++
            //Пока не дойдем до конца списка
            while (j!= list.size()){
                temp=1;
                k = j+1;
                //Пока не k-й индекс списка не дойдет до конца
                while (k!=list.size()){

                    if (list.get(j).equals(list.get(k))) {
                        temp++;
                        //удаляем дубликат из списка по индексу k
                        list.remove(k);
                        //Чтобы избежать выхода за список, меняем положение текущего индекса на прошлый, так как произошло смещение из-за удаления элемента
                        k--;
                    }
                    //переходим к следующему вторичному элементу сравнения
                    k++;
                }
                //Посредством функции write с дополнением APPEND, отвечающим за функцию дозаписи информации в файл, добавляем новую строку вида:
                //Количество повторений + Значение уникального ключа + Переход на новую строку
                    Files.write(result, (temp + "\t" + list.get(j) + "\n").getBytes(), StandardOpenOption.APPEND);
                //Переход на следующий первичный элемент сравнения (по которому сравнивают)
                j++;
            }
            //Дописываем EOF в конце для условного разделения модулей в файле
            Files.write(result, ("EOF\n").getBytes(), StandardOpenOption.APPEND);
        }

        //метод для тестового вывода информации из файла на консоль
        private void printList(List<String> list){
            for (String str : list)
                System.out.println(str);
        }
    }
}
