# Problem Question: https://codingcompetitions.withgoogle.com/kickstart/round/0000000000435914/00000000008da461
# Submitted by me in Round H Google Kickstart 2021 - All passed - 1st Question in Kickstart

t = int(input())

'''
Logic:

As S may contain duplicate chars, and we know for a distinct char in S, there will be only 1 minimum distance between that char and characters in F.

So if we loop for all distinct chars in S and F in nested way (to find the distance), 
then worst case will be 26*26.

To get nearest distance, it can be minimum of the direct distance and the circular distance.
For circular distance, we sum the distance from bigger character to 122 and distance from 97 to smaller char.
'''

for case in range(t):
    ans = 0
    s = input()
    f = input()
    
    occurrence = {}
    for char in s:
        if char in occurrence:
            occurrence[char] += 1
        else:
            occurrence[char] = 1
    
    distinct_str = ''.join(set(s))
    mapping = {}
    
    for charS in distinct_str:
        mapping[charS] = 0 if charS in f else 100 # initialise to 0 if charS is there in f
        # else initialise to a greater number (greater than 26)
        
        for charF in f:
            if charS == charF:
                # same char is found in f, then 0 is the distance, already saved in mapping
                continue
            
            ordS = ord(charS)
            ordF = ord(charF)
            
            smaller_char = min(ordS, ordF)
            bigger_char = max(ordS, ordF)
            mapping[charS] = min(mapping[charS], abs(ordS - ordF), (122-bigger_char)+(smaller_char - 97 + 1))
    
    # for each char, we multiply the distinct char's mapping and their respective number of occurrences..
    for char, dist in mapping.items():
        ans += occurrence[char] *dist
        
    print('Case #{}: {}'.format(case + 1, ans))
    
