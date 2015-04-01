Given a messenger
When set server to wp.pl
Then testConnection should return 0

When set server to wp.com
Then testConnection should return 1

When set parameters server and message to wp.pl message
Then send should return 0

When set parameters server and message to wp.com message
Then send should return 1

When set parameters server and message to w message
Then send should return exception 2