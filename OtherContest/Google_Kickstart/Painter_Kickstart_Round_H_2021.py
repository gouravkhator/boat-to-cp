# Problem Question: https://codingcompetitions.withgoogle.com/kickstart/round/0000000000435914/00000000008d9a88
# Submitted by me in Round H 2021 Google Kickstart - All Passed - 2nd Question in Kickstart

t = int(input())

colors_map = {
    'U': set(),
    'R': {'R'},
    'Y': {'Y'},
    'B': {'B'},
    'O': {'R', 'Y'},
    'P': {'R', 'B'},
    'G': {'Y', 'B'},
    'A': {'R', 'Y', 'B'}
}

'''
Logic:

Firstly, check the colors for each character given.
Then, check the streak of red, streak of yellow, and streak of blue..

For each streak, answer is incremented by one.
For each current color, if that color is absent in colors set of prev char, then answer is incremented by one.

Update prev_colors to point to current colors.
'''

for case in range(t):
    ans = 0
    
    n = int(input())
    s= input()
    
    prev_colors  = set()
    
    for char in s:
        curr_colors = colors_map[char]
        
        for color in curr_colors:
            if color not in prev_colors:
                ans += 1
        
        prev_colors = curr_colors
    
    print('Case #{}: {}'.format(case + 1, ans))
    
