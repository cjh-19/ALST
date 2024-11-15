#include<iostream>
#include<vector>
#include<algorithm>

using namespace std;

vector<int> box(100);

int solve(int dump) {
    for (int i = 0; i < dump; i++) {
        // 오름차순 정렬해서 box[99]은 1감소, box[0]은 1증가
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
        box.clear(); // 사이즈까지 0이 됨
        box.resize(100);
        for (int i = 0; i < 100; i++) {
            cin >> box[i];
        }
        cout << "#" << test_case << " " << solve(dump) << "\n";
    }
    return 0;
}