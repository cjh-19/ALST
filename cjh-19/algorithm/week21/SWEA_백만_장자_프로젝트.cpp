#include <iostream>
#include <stack>

using namespace std;

long long calculate(stack<int> st) {
    long long result = 0;
    long long max_price = st.top(); // 맨 마지막 숫자를 최고 가격으로 설정
    st.pop();

    while (!st.empty()) {
        int num = st.top();
        st.pop();
        if (max_price < num) {
            max_price = num;
        }
        else {
            result += max_price - num;
        }
    }

    return result;
}

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
        stack<int> st;
        for (int i = 0; i < N; i++) {
            int num;
            cin >> num;
            st.push(num);
        }
        cout << "#" << test_case << " " << calculate(st) << "\n";
    }
    return 0;
}