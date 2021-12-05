import math

'''
Question:
(Mainly given in Clement's Youtube Video on Google Coding Interview with A normal software engineer: https://youtu.be/rw4s4M3hFfs )

Given a requirements list which contains the special requirements you need near your apartment.
Given a blocks list each containing 1 apartment, and each apartment can have any or none of the requirements you asked.
The blocks list is a list of dictionaries with special requirements as False or True, representing whether that requirment is there in the respective block or not.

You are needed to minimize the farthest distance you would go from a particular block to gather all your special requirements.
Note: the dictionaries in the blocks list contains all the requirements as given in the requirements list with either False or True.

So, if requirements is ["gym", "school"]
Then blocks cannot be : [
    {"gym": False}, {"gym": True, "school": True}
]

First dictionary should also contain school as the key, with value False or True.

Ex- 
    requirements = ["gym", "school", "store"]
    blocks = [
        {
            "gym": False,
            "school": True,
            "store": False
        },
        {
            "gym": True,
            "school": False,
            "store": False
        },
        {
            "gym": True,
            "school": True,
            "store": False
        },
        {
            "gym": False,
            "school": True,
            "store": False
        },
        {
            "gym": False,
            "school": True,
            "store": True
        }
    ]
Then, from block at index 3, we can have school requirement fulfilled.
ANd if we go 1 index less, we have gym fulfilled. And 1 index more than 3, we have store fulfilled.
So, at 2, we got : gym. At 3, we got : school. At 4, we got: store.
So, farthest minimum distance is 1. (as from index 3, we have to travel 1 block at max).
'''

'''
Logic: (all logic thought and coded by me)

For getting all requirements, we need to get a smallest cluster (or subset) which contains all requirements.
So, if we traverse the blocks list in forward direction and then save each requirement's last seen index:

At each block, we check and update the requirement's last seen index, the latest index at which this requirement was True.
And at that block, we then check if all requirements have been last seen.
If yes, then update minDist to ceil((max of the lastSeenIndices - min of lastSeenIndices) / 2)

It is because we got a subset which has all requirements fulfilled.
Subset starts with min(lastSeenIndices) as the starting index and ends at max(lastSeenIndices) as the ending index.

And if we go from middle of the subset to both the endpoints, we get a minimized farthest distance to be travelled.
So we divide the max - min by 2 and get the ceil.
Ex- if end of required subset is 4 and start is 1, then max - min is 4 - 1 = 3 and then if we divide by 2, we get 1.5
So, we get farthest distance to be ceil(1.5) = 2.
So, in subset, we can start from index 2 or index 3, we get same farthest distance that is, 2.

Also, why ceil is used ?
Ex- if subset starts at index 0 and ends at index 1, then max - min = 1 - 0 = 1
And then 1//2 = 0.
So, minDist becomes 0, but actually the farthest distance was 1.
So, we ceil up the result.

Do similar things as above but in backward pass.
(it is necessary because the requirements can be satisfied at a much smaller distance,
if most requirements are True in the blocks near the end of blocksList).

So, maybe we can have minimum farthest distance when traversing backwards, so we do both forward and backward pass.
If minDist was same as the initial value, then it means it was not set in any of the passes.
It also means, that some of the requirements were False in all of the blocks given.
So, we return -1. 
'''

def minDistToSpecialBlock(blocks, requirements):
    blocksLen = len(blocks)
    totalReqs = len(requirements)
    lastSeenIndices = [-1]*totalReqs

    minDist = blocksLen + 2 # anything greater than blocks length, to initialise minimum distance

    # forward pass through the blocks list
    for index, block in enumerate(blocks):
        # check and update the lastSeenIndices for each requirement
        for reqIndex, req in enumerate(requirements):
            if block[req] == True:
                lastSeenIndices[reqIndex] = index
        
        if -1 not in lastSeenIndices:
            # if -1 that was initialised in lastSeenIndices is not there, that means
            # all requirements have been last seen already
            # and we got the needed subset
            minDist = min(minDist, math.ceil( (max(lastSeenIndices) - min(lastSeenIndices)) / 2 ))

    lastSeenIndices = [-1]*totalReqs # reinitialise lastSeenIndices to all -1

    # backward pass through the blocks list
    for index in range(blocksLen - 1, -1, -1):
        block = blocks[index]

        # check and update the lastSeenIndices for each requirement
        for reqIndex, req in enumerate(requirements):
            if block[req] == True:
                lastSeenIndices[reqIndex] = index
        
        if -1 not in lastSeenIndices:
            # we got a subset where all the nodes are visited now
            minDist = min(minDist, math.ceil( (max(lastSeenIndices) - min(lastSeenIndices)) / 2 ))

    if minDist == blocksLen + 2:
        # it means that some requirements were not True in any of the given blocks
        # so valid subset was not found
        # and so, minDist was not set in any passes
        return -1

    return minDist

if __name__ == '__main__':
    requirements = ["gym", "school", "store"]
    blocks = [
        {
            "gym": False,
            "school": True,
            "store": False
        },
        {
            "gym": True,
            "school": False,
            "store": False
        },
        {
            "gym": True,
            "school": True,
            "store": False
        },
        {
            "gym": False,
            "school": True,
            "store": False
        },
        {
            "gym": False,
            "school": True,
            "store": True
        }
    ]

    minDist = minDistToSpecialBlock(blocks=blocks, requirements=requirements)
    if minDist == -1:
        print("Some of your special requirements were not fulfilled in the blocks provided..")
    else:
        print("Minimum farthest distance to travel to all special blocks: ", minDist)
