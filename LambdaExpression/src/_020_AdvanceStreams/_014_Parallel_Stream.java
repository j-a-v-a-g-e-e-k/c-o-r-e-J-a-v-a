package _020_AdvanceStreams;

import java.util.Arrays;
import java.util.List;

import _099_helper.TimeUtil;

public class _014_Parallel_Stream {

	public static void main(String[] args){
		List<Integer> numbers = Arrays.asList(1,2,3,4,5,6,7,8,9,10);

		TimeUtil.timeIt(() ->
		System.out.println(
				numbers.stream()
				.filter(e -> e%2==0)
				.mapToInt(e -> { 
					try{ Thread.sleep(1000); } catch(Exception ex) {}
					return e*2;})
				.sum()));


		TimeUtil.timeIt(() ->
		{
			numbers.parallelStream()
			.filter(e -> e%2==0)
			.mapToDouble(e -> { 
				try{ Thread.sleep(1000); } catch(Exception ex) {}
				return e*2.0;})
			.sum();});
	}

}
