'''
Test cases:

2
[[1,0]]
4
[[1,0],[2,0],[3,1],[3,2]]
1
[]
5
[[2,0],[2,1],[2,4],[3,1],[4,2],[4,3]]
5
[[0,4],[2,1],[2,4],[2,0],[3,0],[3,1],[4,3]]
'''

class CourseScheduleII:
    # this method is a copy of Leetcode course schedule question, and my submission for that question..
    # coursesFinishable method returns true if all courses can be finished..
    def coursesFinishable(self, numCourses, preMap):
        visitedSet = set()
        
        # do dfs on the course, and see if from that course, we are getting loop, or we can easily finish that course.
        def dfs(course):
            if course in visitedSet:
                # course was already visited, loop detected, so cannot finish that course
                return False
            
            if preMap[course] == []:
                # that means we have finished its prerequisite, or it didn't have any prerequisiste
                return True
            
            # or else, loop and recur through the prerequisite and finish it as we would have done that
            # visit that course, for further loop checking
            visitedSet.add(course)
            for prereq in preMap[course]:
                # if dfs(prereq) returns False, it means that prereq course cannot be finished, so current course cannot be finished either
                if not dfs(prereq): return False
            
            visitedSet.remove(course)
            '''
            Why removing course from visitedSet?
            > It's bcoz let's say from 0th course, we have course 2 and 3 as prerequisite. But by some chance and recursion, course 2 is done.
            
            Now, for course 3, course 2 is given as prerequisite.
            Now, course 2 is already in visitedSet, so it will return False, meaning we found a loop.
            But, there was no loop.
            It was that 2 was on different chain and 3 was on different chain. So, remove course from visited once it's done.
            '''
            
            preMap[course] = [] # emptying the prereq list, so that in future traversals, we can easily tell that this course can be finished, no need to recur
            return True # all cases are covered, so now this course can be finished
        
        # as the graph might be disconnected, so run the dfs on each course
        for course in range(numCourses):
            if not dfs(course): return False
        
        return True
    
    # Problem Question: https://leetcode.com/problems/course-schedule-ii/
    def findOrder(self, numCourses: int, prerequisites: List[List[int]]) -> List[int]:
        '''
        Logic:

        First check if courses can be finished or not..
        If yes, then do topological sort, else return []

        For topological sort, get inspiration from geeksforgeeks diagram..

        Why we have done topological sort, and not normal recursion?
        > It's bcoz for getting ordering of the nodes in the graph of prerequisites, topological sort uses saving and getting approach.
        Normal recursion might have traversed each node multiple times, and not saved the state of that node.
        Topological sort saves the state, and does not re-process for each node more than once.

        Topological sort means to have dependent ones in the bottom of stack, and the non-dependent ones in the top of stack.
        So, for this, the prerequisites are pushed to stack, after the dependent ones are pushed to stack.
        So, we recur from prerequisite courses to their next courses. And if the next courses are already in stack, we just push this prerequisite course too.
        Else, we again take that next course to be prerequisite course for further courses.
        '''
        
        adjList = {course: [] for course in range(numCourses)} # initializing adjacency list
        preMap = {course: [] for course in range(numCourses)} # initializing prerequisite map, similar to adjList
        
        # populate adjList and preMap
        for course, prereq in prerequisites:
            # connection is from prereq to course
            adjList[prereq].append(course) # adjList maps from prereq to course
            preMap[course].append(prereq) # preMap has course as key, and its prerequisites in its value list
        
        if not self.coursesFinishable(numCourses, preMap):
            # if courses are not finishable due to any found loop, then return []
            return []
        
        visited = set()
        stack = []
        
        # for topological sort, check out geeksforgeeks article and only check the diagram given there..
        # the below code is mine, inspired from diagram..
        def topologicalSort(course):
            if course in visited:
                return
            
            visited.add(course)
            for i in adjList[course]:
                topologicalSort(i)
            
            stack.append(course)
        
        # doing topological sort, to get the order of courses
        # do topological sort from each course, as we need to cover every node, and no node should be undiscovered..
        for i in range(numCourses):
            topologicalSort(i)
        
        return stack[::-1] # print stack from top to bottom, in reverse order
