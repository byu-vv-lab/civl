/* This header file defines standard types and provides
 * function prototypes used in the CIVL-C language.
 */
 
#ifdef __CIVLC__
#else
#include<civlc-common.h>
#define __CIVLC__

/* creates a new message, copying data from the specified buffer */ 
$message $message_pack(int source, int dest, int tag,
    void *data, int size) {
  $message result;
  
  result.source = source;
  result.dest = dest;
  result.tag = tag;
  result.data = $bundle_pack(data, size);
  result.size = size;
  return result;
}
  
/* returns the message source */ 
int $message_source($message message) {
  return message.source;
}

/* returns the message tag */
int $message_tag($message message) {
  return message.tag;
}

/* returns the message destination */ 
int $message_dest($message message) {
  return message.dest;
}

/* returns the message size */ 
int $message_size($message message) {
  return message.size;
}

/* transfers message data to buf, throwing exception if message
 * size exceeds specified size */ 
void $message_unpack($message message, void *buf, int size) {
  $bundle_unpack(message.data, buf);
  $assert(message.size <= size, 
    "Message of size %d exceeds the specified size %d.", message.size, size);
}

/* Returns the place of the local communicator.  This is the same as the
 * place argument used to create the local communicator. */
int $comm_place($comm comm){
 return comm->place;
}

void $barrier_call($barrier barrier) {
  $barrier_enter(barrier);
  $barrier_exit(barrier);
}
#endif
