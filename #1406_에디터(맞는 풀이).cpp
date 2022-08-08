#include <iostream>
#include <string>
#include <stack>
using namespace std;

int main() {
	string str;
	int n;
	cin >> str;
	
	cin >> n;
	stack<char> left, right;
	string com;
	
	for (size_t i = 0; i < str.size(); i++)
	{
		left.push(str.at(i));
	}
	
	for (size_t i = 0; i < n; i++)
	{
		cin >> com;
	

		if (com == "L") {
			if (!left.empty()) {
				right.push(left.top());
				left.pop();
			}
		}
		else if (com == "D") {
			if (!right.empty()) {
				left.push(right.top());
				right.pop();
			}
		}
		else if (com == "B") {
			if (!left.empty()) {
				left.pop();
			}
		}
		else {
			char c;
			cin >> c;
			left.push(c);
		}
	}

	
	while(!left.empty())
	{
		right.push(left.top());
		left.pop();
	}
	
	while(!right.empty())
	{
		cout << right.top();
		right.pop();
	}

	return 0;

}
