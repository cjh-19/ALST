#include<iostream>
#include<vector>
#include<algorithm>

using namespace std;

vector<int> box(100);

int solve(int dump) {
    for (int i = 0; i < dump; i++) {
        // �������� �����ؼ� box[99]�� 1����, box[0]�� 1����
        sort(box.begin(), box.end());
        box[99]--;
        box[0]++;
    }
    sort(box.begin(), box.end());
    return box[99] - box[0];
}

int main(int argc, char** argv)
{
    for (int test_case = 1; test_case <= 10; ++test_case)
    {
        int dump;
        cin >> dump;
        box.clear(); // ��������� 0�� ��
        box.resize(100);
        for (int i = 0; i < 100; i++) {
            cin >> box[i];
        }
        cout << "#" << test_case << " " << solve(dump) << "\n";
    }
    return 0;
}