class CourseSchedule:
    # the below approach was understood and then tried from a youtube video, on same question
    # Problem Question: https://leetcode.com/problems/course-schedule/
    def canFinish(self, numCourses: int, prerequisites: List[List[int]]) -> bool:
        '''
        Logic:
        
        Main core logic is same as how we complete our courses.
        First we take 0th course, check it's prerequisite, and if found, we recur for that course. We complete them, and once every course in the prerequisite list is done, we empty that. So that, we make sure, that course is done, if we again want to check.
        
        Maybe, some nodes are disconnected. So, we do dfs on each course to find cycle.
        
        Why we did not do topological sort?
        > It's bcoz topological sort provides ordering of the courses,
        but to check if courses can be finished or not,
        we have to apply the logic we normally do in schedluing our own subjects in real life..
        '''
        
        preMap = {course: [] for course in range(numCourses)} # initializing prerequisite map for each node
        # populate preMap
        for course, prereq in prerequisites:
            preMap[course].append(prereq)
        
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
