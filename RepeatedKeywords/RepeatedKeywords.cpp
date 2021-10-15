#include <iostream>
#include <fstream>
#include <list>
#include <string>
#include <iterator>
using namespace std;

class File {
	ofstream result;
	string temp, tmp;
	//Объявляем список класса STL, содержащий элементы строкового типа
	list <string> L;
public:
	//функция чтения из файла ключей до перехода каретки и записи их в список 
	void ReadingFile(){
		ifstream file("file.txt");
		//ios::app позволяет включить дописывание в файл вместо перезаписи по-умолчанию
		result.open("result.txt", ios::app);
		while (!file.eof()) {
			//список для хранения ключевых значений
			temp = "";
			while (temp != "EOF") {
				getline(file, temp);
				if (temp != "EOF")
				L.push_back(temp);
			}
//			if(!L.empty())
			UniqFunction();
		}
	}

	//функция для определения уникальных ключей списка и последующая их запись в файл
	void UniqFunction() {
		//Итераторы для перехода между элементами списка
		list <string> :: iterator it;
		list <string> ::iterator it_temp;
		it = L.begin();
		it_temp = L.begin();
		int temp=1;

		while (it!=L.end()) {
			it_temp++;
			temp = 1;
			
			while (it_temp!=L.end()) {
				if (*it == *it_temp) {
					temp++;
					it_temp = L.erase(it_temp);
					it_temp--;
				}
				it_temp++;
			}
			
			//Записываем в файл результат обработки
			result << *it << "\t" << temp <<"\n";
			//Перемещаем итераторы
			it++;
			it_temp = it;
		}
		//Записываем разделитель в файл и очищаем список
		result << "EOF\n\n";
		L.clear();
	}
};

int main()
{
	File file;
	file.ReadingFile();
}