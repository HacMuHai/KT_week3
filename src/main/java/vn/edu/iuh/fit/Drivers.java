package vn.edu.iuh.fit;

import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Collection;

import jdepend.framework.JavaPackage;
import jdepend.framework.PackageFilter;

public class Drivers {
	public static void main(String[] args) throws Exception {
//		LCOM4Calculation calculation = new LCOM4Calculation();
//		File file = new File("T:\\KT_week2\\target\\classes\\vn\\edu\\iuh\\fit\\ckjm\\MetricsFilterTest.class");
//		List<Group> lst = calculation.loadGroups(file);
//		lst.forEach(System.out::println);
//		int lcom4 = calculation.loadGroups(file).size();
//		System.out.printf("LCOM4 of class %s is %d\n", file.getName(), lcom4);

		jdepend.framework.JDepend d = new jdepend.framework.JDepend();
		d.addDirectory("T:\\KT_week2");
		Collection<JavaPackage> cols = d.analyze();
		cols.forEach(pkg -> {
			System.out.printf("Pakage %s, Ca= %d; Ce=%d;\n", pkg.getName(), pkg.getAfferents().size(),
					pkg.getEfferents().size());
		});

		try (PrintWriter out = new PrintWriter(new FileOutputStream("results.xml"))) {
			jdepend.xmlui.JDepend xml = new jdepend.xmlui.JDepend(out);
			xml.addDirectory("T:\\KT_week2");
			PackageFilter f = PackageFilter.all();
			// f.including("vn.edu.iuh");
			f.accept("vn.edu");
			f.excluding("org");
			xml.setFilter(f);
			xml.analyze();
		}
	}
}