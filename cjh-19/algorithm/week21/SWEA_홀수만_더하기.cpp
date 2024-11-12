#include<iostream>

using namespace std;

int main(int argc, char** argv)
{
	int test_case;
	int T;

	cin >> T;

	for (test_case = 1; test_case <= T; ++test_case)
	{
		int num;
		int result = 0;
		for (int i = 0; i < 10; i++) {
			cin >> num;
			if (num % 2 != 0) result += num;
		}

		cout << "#" << test_case << " " << result << "\n";
	}
	return 0;
}