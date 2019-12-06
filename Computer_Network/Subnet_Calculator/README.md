# Subnet Calculator
*Written on 10 April 2019.*

Subnet Calculator written in Python using os, sys, subprocess and socket libraries.

Uses IPaddress/CIDR as input (extracts the information from current computer if not given)
and prints network address, network class, network type, subnet mask, broadcast address,
first and last host address and max number of hosts (all addresses both decimal and binary).

If given address is pingable the program asks if you want to ping it, then shows the result.

All data is saved to a text file.
