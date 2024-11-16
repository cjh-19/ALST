#include<iostream>
#include<string>

using namespace std;

int main(int argc, char** argv)
{
    ios_base::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    int test_case;
    int T;

    cin >> T;

    for (test_case = 1; test_case <= T; ++test_case)
    {
        int N;
        cin >> N;
        int cnt = 0;
        int sum = 0;
        for (int i = 0; i < N; i++) {
            string num;
            cin >> num;
            int mid = (N - 1) / 2;

            // 합 구하기
            for (int j = mid - cnt; j <= mid + cnt; j++) {
                sum += num[j] - '0';
            }

            if (i < mid) cnt++;
            else cnt--;
        }
        cout << "#" << test_case << " " << sum << "\n";
    }
    return 0;
}