
def charclass(c):
    '''Turns character c into one of '<', '>', '\' and '.'. '''
    return '.' if c != '<' and c != '>' and c != '\\' else c

# These are the transitions, start state is 0 (See notes)
T = { (0,'<'):(1,'nada'), 
      (0,'\\'):(2,'nada'),
      (0,'>'):(0,'copy'),
      (0,'.'):(0,'copy'),
      (1,'>'):(0,'nada'),
      (1,'<'):(0,'redact'),
      (1,'.'):(1,'redact'),
      (1,'\\'):(3,'nada'),
      (2,'.'):(0,'copy'),
      (2,'<'):(0,'copy'),
      (2,'>'):(0,'copy'),
      (2,'\\'):(0,'copy'),
      (3,'.'):(1,'redact'),
      (3,'<'):(1,'redact'),
      (3,'>'):(1,'redact'),
      (3,'\\'):(1,'redact') }

def process(hin,hout):
    '''Reads from text stream hin, and write the "redacted" 
       form of that input to the text stream hout.'''
    state = 0
    while(True):
        c = hin.read(1)
        if (c == ''):
            break
        state,action = T[(state,charclass(c))]
        if action != 'nada':
            hout.write('☒' if action == 'redact' else c)

if __name__ == "__main__":
    import sys
    process(sys.stdin,sys.stdout)
