# Botnet Crawler

## Background
You’re a hacker which is currently controlling 10k hosts using your own Trojan software. You have full control over each host. 
Unfortunately, as a poor hacker, you’re getting a free Raspberry Pi 1 Model A as the only centralized master for controlling these hosts.

Tech spec listed below:
* CPU: 700 MHz
* Memory: 128 MB
* Network: 5 MB/s for both upload and download
* Disk: Not available

Each device in the botnet will send a heartbeat every few seconds to your Raspberry Pi if it’s still online. Inside your controller, you can easily know all available hosts in the form of a list of IP addresses and ports. You could execute any command on each host through SSH.

## Problem Statement

You want to crawl wikipedia.com with roughly 1 billion pages in total.
The raw HTML size per page is 100 KB on average. 
Only need to crawl and store raw HTML pages locally (local hard drive). Please be aware that the available disk per host is roughly 40 GB since most of the space should be left for users.
Machines can be turned on and turned off at any time. Failure rate: roughly 1 host will become unavailable per second. Most of the hosts (95%) will be back within 10 minutes.

### Precautions

10k hosts in the botnet are only hardware resources you have. No access to anything which is NOT free (e.g. AWS, Azure, Google Cloud).
Introducing open source tooling which would significantly simplify the design (e.g. HDFS / Spark / Kafka) is strongly discouraged since this against the purpose of this interview.

### Clarifications

You’re guaranteed to have at least 10k hosts at any given time.
No need worrying about page refreshing/updating, just need to crawl all pages once.
No need worrying about pages outside the domain of wikipedia.com.
The only seed page you have to start with is wikipedia.com.
Except for the max hard drive available which we’ve predefined, you want to use as little hardware resources (CPU, memory, network) as possible since most of the resource should be left for users.
The tech spec for each host will be similar to the hosts we use on a daily basis. For your reference, best-selling PC 2020 on Amazon: AMD Ryzen 3 3200U Dual Core (3.5GHz); 4GB DDR4 Memory; 128GB PCIe NVMe SSD. 

### P0 Constraints

Balance the workload between all hosts. All hosts should have relatively similar resources' usage in terms of CPU, memory, network and hard drive IOPS.
Strictly avoid duplicated crawling.
Be able to handle high hardware failure as described in the description.

### P1 Constraints

Tight engineering schedule. After finishing the design, the prototype (MVP) should be implemented within 2 months with 1 engineer.
Minimize the total time to finish the entire crawling job.

### **Goal**

**Design a software system which could crawl and store all pages under wikipedia.com.**

The software system should fit all the constraints.

## Where It was asked/found ?

Was asked in `Facebook` to some programmer. I found this systems design question in leetcode on [this](https://leetcode.com/discuss/interview-question/1162048/facebook-or-system-design-or-onsite-or-E4-or-USA) link.

## Others Approach

One person on leetcode, gave an approach having the following info:
1. use consistent hashing.
2. There will be multiple modules in each host. Main modules are: crawler, deduplication, parser, hash-generator-from-url, etc
3. Each host should have a list of all hosts ip address / hash, so that it can communicate with them.
4. For each link, generate hash and then pass that link to related host which is responsible for that hash.
5. if a host is down, then pi won't get heartbeat, and it will update the database and update all the hosts about this update.
6. To avoid data failure, data should be copied to next host in the ring. For more reliablity we can copy on multiple hosts.
7. When a new host is up. it will let the pi know that its online, and pi will update the database and update all the hosts as well so that they can start communicating with it.
8. There's also need to do some Back-of the envelope calculation: e.g. bandwidth needed per host, disk space per host etc...

## My Approach

***Pending to be thought.***
