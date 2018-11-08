###### Install prometheus:
- grant your user the ability to create required authorization roles:
  - kubectl create clusterrolebinding cluster-admin-binding \
        --clusterrole cluster-admin --user "$(gcloud config get-value account)"
- prometheus installation guide:
  - https://github.com/coreos/prometheus-operator/tree/master/contrib/kube-prometheus 


###### Install helm (k8s package manager):
- brew install kubernetes-helm
- helm init
  - According to https://github.com/fnproject/fn-helm/issues/21 perform:
    - kubectl create serviceaccount --namespace kube-system tiller
    - kubectl create clusterrolebinding tiller-cluster-rule --clusterrole=cluster-admin --serviceaccount=kube-system:tiller
    - kubectl patch deploy --namespace kube-system tiller-deploy -p '{"spec":{"template":{"spec":{"serviceAccount":"tiller"}}}}'

###### Install prometheus-adapter (for resolving custom mterics):
  - helm install --name my-release \
      --set prometheus.url=http://prometheus-k8s.monitoring.svc \
      --set metricsRelistInterval=20s \
      stable/prometheus-adapter
  - ###### check list of exported custom metrics from adapter:
    kubectl get --raw /apis/custom.metrics.k8s.io/v1beta1
    
     
