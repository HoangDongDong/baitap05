package configs;

import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;

public class SiteMeshConfig extends ConfigurableSiteMeshFilter {
	@Override
	protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
		builder
				// ❗️CHỈ ghi "web.jsp" (SiteMesh sẽ tự thêm /WEB-INF/decorators/)
				.addDecoratorPath("/*", "web.jsp")

				// Tránh decorator tự bị decorate
				.addExcludedPath("/WEB-INF/*").addExcludedPath("/assets/*").addExcludedPath("/commons/*")
				.addExcludedPath("/login*").addExcludedPath("/api/*");
	}
}
