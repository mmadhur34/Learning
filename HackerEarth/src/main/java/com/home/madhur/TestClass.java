package com.home.madhur;

import java.util.*;

public class TestClass {
    public static void main(String[] a){
        //Take Input
        Scanner scanner=new Scanner(System.in);
        int passenger=Integer.parseInt(scanner.nextLine());
        List<String> input=new ArrayList<>();
        while(scanner.hasNextLine()){
            String line=scanner.nextLine();
            if(line.equals(""))
                break;
            input.add(line);
        }
        List<List<Integer>> seatBlockPairs=new ArrayList<>();
        for(String line:input){
            int col= Integer.parseInt(line.split(" ")[0]);
            int row= Integer.parseInt(line.split(" ")[1]);
            seatBlockPairs.add(Arrays.asList(col,row));
        }
        SeatingArrangement arrangement=null;
        if(validateInput(passenger,seatBlockPairs))
            arrangement=initializeArrangement(seatBlockPairs,passenger);
        else
            System.out.println("Invalid input");
        //Start seat Allotment
        if(arrangement!=null){
            allot(passenger, arrangement);
            arrangement.showArrangement();
        }




    }
    private static boolean validateInput(int passenger,List<List<Integer>> size){
        if(passenger<0 || passenger>=(10000000))
            return false;
        for(List<Integer> pair:size){
            int c=pair.get(0);
            int r=pair.get(1);
            if(c<0 || r<0 || c>=1000 || r>=1000)
                return false;
        }
        return true;
    }
    private static SeatingArrangement initializeArrangement(List<List<Integer>> input, int passengerCount) {

        SeatingArrangement arrangement= new SeatingArrangement();
        for(int i=0;i<input.size();i++){
            List<Integer> pair=input.get(i);
            SeatBlock seatBlock=new SeatBlock(pair.get(1),pair.get(0),
                    i == 0, i == input.size() - 1);
            if(pair.get(0)==1 && i==0)
                seatBlock.setCanAllocateWindow(false);
            arrangement.addBlock(seatBlock);
        }

        //Show inputs received
//        System.out.println("Passengers "+passengerCount);
//        arrangement.showArrangement();
        return arrangement;
    }
    private static void allot(int passengers, SeatingArrangement arrangement) {
        int currentPassenger=1;
        while (currentPassenger<=passengers ){
            if(arrangement.allocate(currentPassenger))
                currentPassenger++;
            else if(arrangement.isCompletedCurrentType()){
                if(arrangement.getCurrentType().equals(Seat.SeatType.MIDDLE)){
                    break;
                }
                arrangement.setNextType();
                continue;
            }
        }
    }
}

class SeatingArrangement{
    private List<SeatBlock> arrangement;
    private int currentBlock;
    private Seat.SeatType currentType;
    private boolean completedCurrentType;
    SeatingArrangement(){
        arrangement = new ArrayList<>();
        currentBlock = 0;
        currentType = Seat.SeatType.AISLE;
        completedCurrentType=false;
    }
    void addBlock(SeatBlock block) {
        arrangement.add(block);
    }
    void resetBlock(){
        arrangement.clear();
    }

    void setNextType(){
        currentType=currentType.equals(Seat.SeatType.AISLE)?Seat.SeatType.WINDOW:
                Seat.SeatType.MIDDLE;
        currentBlock=0;
    }

    boolean allocate(int passenger) {

        while (currentType == Seat.SeatType.AISLE && arrangement.stream().
                map(x -> x.isCanAlocateAisle()).filter(x -> x).count() > 0) {
            if(arrangement.get(currentBlock).allocate(passenger, currentType)) {
                if(!arrangement.get(currentBlock).isContinueSameBlock()){
                    currentBlock++;
                    if(currentBlock==arrangement.size())
                        currentBlock=0;
                }
                return true;
            }
            else{
                currentBlock++;
                if(currentBlock==arrangement.size())
                    currentBlock=0;
            }
        }
        if(currentType == Seat.SeatType.AISLE && arrangement.stream().
                map(x -> x.isCanAlocateAisle()).filter(x -> x).count() == 0){
            completedCurrentType=true;
            currentBlock=0;
            return false;
        }

        while (currentType == Seat.SeatType.WINDOW && arrangement.stream().map(x ->
                x.isCanAllocateWindow()).filter(x -> x ).count() > 0) {
            if (arrangement.get(currentBlock).allocate(passenger, currentType)){
                if(!arrangement.get(currentBlock).isContinueSameBlock())
                    currentBlock= currentBlock==(arrangement.size()-1)?0:(currentBlock+1);
                return true;
            }else{
                currentBlock= currentBlock==(arrangement.size()-1)?0:(currentBlock+1);
            }
        }
        if(currentType == Seat.SeatType.WINDOW && arrangement.stream().
                map(x -> x.isCanAllocateWindow()).filter(x -> x).count() == 0){
            completedCurrentType=true;
            currentBlock=0;
            return false;
        }
        while (currentType == Seat.SeatType.MIDDLE && arrangement.stream().map(x ->
                x.isCanAllocateMiddle()).filter(x -> x ).count() > 0) {
            if (arrangement.get(currentBlock).allocate(passenger, currentType)){
                if(!arrangement.get(currentBlock).isContinueSameBlock())
                    currentBlock= currentBlock==(arrangement.size()-1)?0:(currentBlock+1);
                return true;
            }else{
                currentBlock= currentBlock==(arrangement.size()-1)?0:(currentBlock+1);
            }
        }
        if(currentType == Seat.SeatType.MIDDLE && arrangement.stream().
                map(x -> x.isCanAllocateMiddle()).filter(x -> x).count() == 0){
            completedCurrentType=true;
            return false;
        }
        return true;
    }

    void showArrangement() {
        int maxRow = getMaxRows();
        StringBuilder rowSeats = new StringBuilder(" ");
        for (int i = 0; i < maxRow; i++) {
            for (SeatBlock anArrangement : arrangement){
                rowSeats.append(anArrangement.showTheRow(i));
                rowSeats.append("|");
            }

            rowSeats.append("\n");
        }
        System.out.print(rowSeats);
    }

    private int getMaxRows() {
        return arrangement.stream().max(
                Comparator.comparingInt(SeatBlock::getRow)).get().getRow();

    }

    public boolean isCompletedCurrentType() {
        return completedCurrentType;
    }

    public Seat.SeatType getCurrentType() {
        return currentType;
    }
}

interface AllocateSeats {

    boolean allocate(int passengerId, Seat.SeatType type);
}

class SeatBlock implements AllocateSeats {
    private Seat[][] seats;
    private int row,column;
    private boolean hasWindowOnLeft;
    private boolean hasWindowOnRight;
    private int currentRow=0,currentColumn=0;
    private boolean canAlocateAisle=true;
    private boolean canAllocateWindow;
    private boolean canAllocateMiddle;
    private boolean continueSameBlock=true;
    SeatBlock(int row,int column,boolean hasWindowOnLeft,boolean hasWindowOnRight){
        this.row=row;
        this.column=column;
        this.hasWindowOnLeft=hasWindowOnLeft;
        this.hasWindowOnRight=hasWindowOnRight;
        seats=new Seat[row][column];
        Seat.SeatType seatType;
        canAllocateWindow = hasWindowOnLeft || hasWindowOnRight;
        canAllocateMiddle = column>2;
        for(int i=0;i<row;i++){
            for(int j=0;j<column;j++){
                if(column==1)
                    seatType= Seat.SeatType.AISLE;
                else if(j==0){
                    seatType=hasWindowOnLeft?Seat.SeatType.WINDOW:Seat.SeatType.AISLE;
                }else if(j==column-1){
                    seatType=hasWindowOnRight?Seat.SeatType.WINDOW:Seat.SeatType.AISLE;
                }else
                    seatType=Seat.SeatType.MIDDLE;
                seats[i][j]=new Seat(seatType);
            }
        }
    }
    /*
    returns true if seat is allocated, sets continueSameBlock to keep in same block
     */
    public boolean allocate(int passenger, Seat.SeatType seatType) {
        switch (seatType){
            case AISLE:
                if (!hasWindowOnLeft&& currentColumn==0 && currentRow<row) {
                    if (!seats[currentRow][0].isAllocated()){
                        seats[currentRow][currentColumn].allocateSeat(passenger);
                        if(hasWindowOnRight){
                            currentRow++;
                            currentColumn=0;
                            continueSameBlock=false;
                        }else{
                            currentColumn=column-1;
                            continueSameBlock=true;
                        }
                        return true;
                    }
                }
                else if(!hasWindowOnRight && currentRow<row){
                    if (!seats[currentRow][column - 1].isAllocated()){
                        seats[currentRow][column - 1].allocateSeat(passenger);
                        currentColumn=0;
                        currentRow++;
                        continueSameBlock=false;
                        if(currentRow==row)
                            canAlocateAisle=false;
                        return true;
                    }
                }
                else{
                    currentColumn=0;
                    currentRow++;
                    if(currentRow>row-1){
                        canAlocateAisle=false;
                        currentRow=0;
                        currentColumn=0;
                    }
                    return false;
                }

                return false;
            case WINDOW:
            {
                if(hasWindowOnLeft && currentRow<row) {
                    if(!seats[currentRow][0].isAllocated()) {
                        seats[currentRow][0].allocateSeat(passenger);
                        if(hasWindowOnRight){
                            currentColumn=column-1;
                            continueSameBlock=true;
                        }else{
                            continueSameBlock=false;
                            currentRow++;
                            currentColumn=0;
                        }
                        return true;
                    }
                    else{
                        if(hasWindowOnRight){
                            currentColumn=column-1;
                            continueSameBlock=true;
                        }else{
                            continueSameBlock=false;
                            currentRow++;
                            currentColumn=0;
                            if(currentRow>=row-1) {
                                canAllocateWindow = false;
                            }
                            currentColumn=1;
                            currentRow=0;
                        }
                        return false;
                    }
                }
                else if(hasWindowOnRight && currentRow<row){
                    if(!seats[currentRow][column-1].isAllocated()) {
                        seats[currentRow][column - 1].allocateSeat(passenger);
                        if(hasWindowOnLeft) {
                            currentColumn = 0;
                        }
                        currentRow++;
                        if(currentRow>row-1) {
                            canAllocateWindow = false;
                            currentColumn=1;
                            currentRow=0;
                        }
                        continueSameBlock=false;
                        return true;
                    }else{
                        if(hasWindowOnLeft) {
                            currentColumn = 0;
                        }
                        currentRow++;
                        if(currentRow>=row-1) {
                            canAllocateWindow = false;
                            currentColumn=1;
                            currentRow=0;
                        }
                        continueSameBlock=false;
                        return false;
                    }

                }
                else
                    canAllocateWindow=false;
                if(currentRow>=row-1) {
                    canAllocateWindow = false;
                }
                currentColumn=1;
                currentRow=0;
                return false;
            }
            case MIDDLE:{
                if(column>=2 && currentColumn<column && currentRow<row){
                    if(!seats[currentRow][currentColumn].isAllocated()) {
                        seats[currentRow][currentColumn].allocateSeat(passenger);
                        currentColumn++;
                        if(currentColumn==column-1){
                            currentColumn=1;
                            continueSameBlock=false;
                            currentRow++;
                            if(currentRow==row){
                                canAllocateMiddle=false;
                            }
                        }else
                            continueSameBlock=true;
                    return true;
                    }else {
                        currentColumn++;
                        if(currentColumn==column-1){
                            currentColumn=1;
                            continueSameBlock=false;
                            currentRow++;
                        }else
                            continueSameBlock=true;
                    return false;
                    }
                }
                else if(currentRow==row-1&& currentColumn==column-1)
                    canAllocateMiddle=false;
                return false;
            }
            default:
                return false;
        }
    }

    String showTheRow(int i) {
        StringBuilder seatStructure=new StringBuilder();
        if(i<this.row){
            for(int j=0;j<column;j++)
                seatStructure.append(seats[i][j]);
        }
        else{
            for(int j=0;j<column;j++)
                seatStructure.append(" ---");
        }
        return seatStructure.toString();
    }

    int getRow(){
        return row;
    }

    boolean isCanAlocateAisle() {
        return canAlocateAisle;
    }

    boolean isCanAllocateWindow() {
        return canAllocateWindow;
    }

    boolean isCanAllocateMiddle() {
        return canAllocateMiddle;
    }

    boolean isContinueSameBlock() {
        return continueSameBlock;
    }
    public void setCanAllocateWindow(boolean canAllocateWindow) {
        this.canAllocateWindow = canAllocateWindow;
    }

}

class Seat {
    enum SeatType {
        WINDOW,
        AISLE,
        MIDDLE;

        @Override
        public String toString() {
            switch (this){
                case AISLE:return " -A-";
                case WINDOW:return " -W-";
                case MIDDLE:return " -C-";
            }
            return " ---";
        }
    }
    private final SeatType seatType;
    private int passengerId=0;
    private boolean isAllocated=false;

    public Seat(SeatType seatType) {
        this.seatType = seatType;
    }

    boolean isAllocated() {
        return isAllocated;
    }

    public SeatType getSeatType() {
        return seatType;
    }
    boolean allocateSeat(int passengerId){
        if(!isAllocated){
            this.passengerId=passengerId;
            this.isAllocated=true;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return isAllocated?getPassengerId():seatType.toString();
    }

    public String getPassengerId() {
        if(passengerId<10)
            return " 00"+passengerId;
        else if(passengerId<100)
            return " 0"+passengerId;
        else if(passengerId<1000)
            return " "+passengerId+"";
        return " ---";
    }
}

