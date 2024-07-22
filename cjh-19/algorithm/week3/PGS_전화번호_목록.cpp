#include <iostream>
#include <string>
#include <vector>
#include <algorithm>

using namespace std;

bool solution(vector<string> phone_book) {
    // 문자열기준 오름차순 정렬
    sort(phone_book.begin(), phone_book.end());

    // 바로 뒤의 부분 문자열과 비교하여 같다면 false 반환
    for (int i = 0; i < phone_book.size() - 1; i++) {
        if (phone_book[i] == phone_book[i + 1].substr(0, phone_book[i].length())) {
            return false;
        }
    }

    return true;
}