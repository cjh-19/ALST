#include<iostream>
#include<vector>
#include<algorithm>
#include<cmath>

using namespace std;

int solve(vector<int> bd) {
    int result = 0;
    // �ε��� 2~bd.size()-3���� �� �ε������� �ڽ� - �翷 ���� �ִ밪���� ��
    for (int i = 2; i <= bd.size() - 3; i++) {
        int maxnum = max(max(bd[i - 2], bd[i - 1]), max(bd[i + 1], bd[i + 2]));
        result += max(bd[i] - maxnum, 0);
    }
    return result;
}

int main(int argc, char** argv)
{
    for (int test_case = 1; test_case <= 10; ++test_case)
    {
        vector<int> building;
        int buildingCnt;
        cin >> buildingCnt;
        for (int i = 0; i < buildingCnt; i++) {
            int num;
            cin >> num;
            building.push_back(num);
        }
        cout << "#" << test_case << " " << solve(building) << "\n";
    }
    return 0;
}