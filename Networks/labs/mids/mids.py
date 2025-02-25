# Filename: Mids.py
# Author: Ian Coffey (m261194)
# To Frontend Query Schedule in Mids

# Import Libraries
import requests

# Request Alpha
alpha = input("Enter Alpha: ")

# URL
url = "https://mids.usna.edu/ITSD/mids/drgwq010$mids.actionquery"

# Form
header = { 
    
    "accept" : "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7",
    "accept-encoding" : "gzip, deflate, br, zstd",
    "accept-language" : "en-US,en;q=0.9",
    "cache-control" : "max-age=0",
    "connection" : "keep-alive",
    "content-type" : "application/x-www-form-urlencoded",
    "cookie" : "f5_cspm=1234; WSG$DRGWQ010$URL0=/ITSD/mids/drgwq010$.startup; WSG$DRGWQ010$CAP0=Schedules_-_Query_Midshipmen; nmstat=39e44132-78c7-9e46-acd6-c14c4328e8f2; _ga=GA1.2.2058454920.1714159019; _ga_LY79N0FLBS=GS1.1.1725537277.2.1.1725537323.0.0.0; BIGipServermids_prod=!c/QAXLxIZCs9soJHrP/1DhKiDM7x/gtZo8YMeJd7tWOV4AgfS6BSyKSvhDXLuaSuE2WG0ygItCeQHT4=; f5avraaaaaaaaaaaaaaaa_session_=FMECGKJDAKILLIAGBOBKKNFFMHBEJPKKDNOJBLNFFCAMDOJCKDEOLLDIGCFEHIINEOHDGHFGMANKGAPOLCMACGKOOBNBNMMDPBBANPFCBGOEEIKCAHEMCHEJKJNBCGLK; OAMAuthnCookie_mids.usna.edu:443=f9yX3hgdPlivsVrj105x8SYhNnciy0Yx%2FPJKyEAO%2FQCfLlrKKNPG%2ByAGWSbGKYqwc%2BCVYUxWopimPzA3hiPd8YJ5HAvcc1dS3UJMXwWCuWDhVXTuCtAhx%2F8XYRRNV%2Fd8G5YQY9syJVXr%2Fu%2B5niyJfdS10n69G3q4DKgNRyEjMTqYloc6Jewg3W5v52u43oaiI2j53ExP%2Fks2EU1gHlB6LFcPCYQPEzj1AY7LBhWxq04GnfvXjKIDwGfkHfZP9dIgvehVEpAW6pwUWVK8uMuDx696FwhBqMkVfcCtE2vovnz3F4%2BvPCU9u%2FhUYpp3eBBMmXJJ3wXu6ffeAA3Di1rFDHOG3edf8bAH60kdS5UgAAC7A5jRLQvfzgKJ0uTSNOTh6cHPqwFyAs%2BF4s2%2FkHDsaSJeybpCl3Tayea4KJGe65C4mVMEWMkSOuUqVfSViKGU9vF4xzCmyDttWCisdbw0CTHSF2jrnCkXr2r%2BRKujujmkC1oWLZDEC1vAG%2B3IlW1XzBcDwbhLQI8Q2NCIqNj%2FlRmM7v4o3y1XW1rxUiL6ZPAia9tBpWD7L8Npnzfu55ONV5dfa958Y2Y3kdAXiOJwdQ%3D%3D",
    "host" : "mids.usna.edu",
    "origin" : "https://mids.usna.edu",
    "referer" : "https://mids.usna.edu/ITSD/mids/drgwq010$.startup",
    "sec-ch-ua-mobile" : "?0",
    "sec-ch-ua-platform" : "Windows",
    "sec-fetch-dest" : "document",
    "sec-fetch-mode" : "navigate",
    "sec-fetch-site" : "same-origin",
    "sec-fetch-user" : "?1",
    "upgrade-insecure-requests" : "1",
    "user-agent" : "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/128.0.0.0 Safari/537.36",
}

# Payload
payload = {
    "P_ALPHA" : f"{alpha}",
    "P_SECOF_COOF_SEBLDA_AC_YR" : "2025",
    "P_SECOF_COOF_SEBLDA_SEM" : "FALL",
    "P_SECOF_COOF_SEBLDA_BLK_NBR" : "1",
    "Z_ACTION" : "QUERY",
    "Z_CHK" : "0",
}

# Create response
response = requests.post(url, data=payload, headers=header, verify=False)

# Output response to html
file = open('output.html', "w")
file.write(response.text)

