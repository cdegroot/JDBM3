package net.kotek.jdbm;

import java.io.*;
import java.nio.ByteBuffer;

/**
 *
 */
interface Storage {

    /**
     * Bite shift used to calculate block size.
     * If you want to modify block size, do it here.
     *
     *  1<<9 = 512
     *  1<<10 = 1024
     *  1<<11 = 2048
     *  1<<12 = 4096
     */
    int BLOCK_SIZE_SHIFT = 12;
    
    /**
     * the lenght of single block.
     * <p>
     *!!! DO NOT MODIFY THI DIRECTLY !!!

     */
    int BLOCK_SIZE = 1<<BLOCK_SIZE_SHIFT;


    /**
     * use 'val & OFFSET_MASK' to quickly get offset within the block page;
     */
    long OFFSET_MASK = 0xFFFFFFFFFFFFFFFFL >>> (64-Storage.BLOCK_SIZE_SHIFT);


    void write(long pageNumber, ByteBuffer data) throws IOException;

    ByteBuffer read(long pageNumber) throws IOException;

    void forceClose() throws IOException;

    boolean isReadonly();

    DataInputStream readTransactionLog();

    void deleteTransactionLog();

    void sync() throws IOException;

    DataOutputStream openTransactionLog() throws IOException;
}
