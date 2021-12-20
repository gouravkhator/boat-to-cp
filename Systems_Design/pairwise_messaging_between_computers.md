# Pairwise Messaging Between Computers

## Problem Statement

Suppose you have N computers running the same algorithm and have pairwise messaging and a resource counter that they all "share". You can send a decrement `<uid>` message to any one of the sites to ask for the site to decrement the shared resource counter. The decrement `<uid>` is initially tentative, but can later be set to committed or canceled. You can also send a cancel `<uid>` to any site on which the corresponding decrement `<uid>` isn't known to be in the committed state. The sites should communicate with each other by sending their entire log of decrement `<uid>` and cancel `<uid>` events, and should union the received log with their own. Messages should only be sent on a send `<node>` command. cancel any decrement `<uid>` as soon as you know that it will make the resource counter negative. Assuming there is a finite duration between messages between any two sites, all tentative decrement `<uid>` must eventually either be committed or canceled. You should also ensure that at all times, if we were to replay all committed decrement operations as if they were applied on a single site, that the resource counter never becomes negative. 

**Find a condition that allows you to set the the committed or canceled state.**

## Where It was asked ?

Got asked to someone in a jane street interview. 

I got to know this in binarysearch platform's #system-design community.

## Others Approach

One person said on binarysearch platform that he would adapt [this](https://hashingit.com/elements/research-resources/1984-08-p233-wuu.pdf) algorithm.

## My Approach

***Pending to be thought.***
