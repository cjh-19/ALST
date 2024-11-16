#include<iostream>
#include<vector>
#include<algorithm>
#include<limits.h>

using namespace std;

int main(int argc, char** argv)
{
    for (int test_case = 1; test_case <= 10; ++test_case)
    {
        int tc;
        cin >> tc;
        vector<int> col(100); // ���� ��
        vector<int> row(100); // ���� ��
        vector<int> dig(2); // �밢���� ��
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                int num;
                cin >> num;
                col[j] += num;
                row[i] += num;
                if (i == j) dig[0] += num;
                if (i + j == 99) dig[1] += num;
            }
        }

        // �ִ밪 ã��
        sort(col.begin(), col.end(), greater<>());
        sort(row.begin(), row.end(), greater<>());
        sort(dig.begin(), dig.end(), greater<>());

        int maxSum = INT_MIN;
        maxSum = max(maxSum, max(col[0], max(row[0], dig[0])));

        cout << "#" << tc << " " << maxSum << "\n";
    }
    return 0;
}