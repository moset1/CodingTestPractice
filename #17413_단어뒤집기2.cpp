#include <iostream>
#include <string>
#include <stack>

using namespace std;

int main() {
	string str;
	getline(cin, str);
	int start, end;
	stack<string> one;

	for (int i = 0; i < str.size(); i++) {
		if (str[i] != '<' && str[i] != ' ') {
			one.push(str.substr(i,1));
		}
		else if (str[i] == ' ') {
			while (!one.empty()) {
				cout << one.top();
				one.pop();
			}
			cout << ' ';
		}
		else {
			while (!one.empty()) {
				cout << one.top();
				one.pop();
			}

			start = i;
			end = str.find('>', start);
			string tmp = str.substr(start, end-start+1);
			cout << tmp;
			i = end;
		}
	}

	while (!one.empty()) {
		cout << one.top();
		one.pop();
	}

	return 0;
}


